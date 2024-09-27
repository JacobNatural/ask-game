package com.app.repository.impl;

import com.app.player.Player;
import com.app.player.PlayerMapper;
import com.app.repository.PlayerRepository;
import com.app.txt.load.FileReader;
import com.app.txt.model.PlayerData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link PlayerRepository} interface using a concurrent map for thread-safe player storage.
 *
 * <p>This class is responsible for loading player data from a specified file and providing methods to manage
 * player records, including adding, removing, and retrieving players.</p>
 */
@Component
public class PlayerRepositoryImpl implements PlayerRepository<Long> {

    private final ConcurrentMap<Long, Player> players;

    /**
     * Constructs a new PlayerRepositoryImpl.
     *
     * @param filename the name of the file from which to load player data
     * @param fileReader     the loading strategy for player data
     */
    public PlayerRepositoryImpl(@Value("${playersFilename}") String filename, FileReader<PlayerData> fileReader) {
        players = fileReader
                .load(filename)
                .stream()
                .map(playerData -> new Player(playerData.login(), playerData.statistics()))
                .collect(Collectors.toConcurrentMap(
                        PlayerMapper.toId::applyAsLong, Function.identity()));
    }

    /**
     * Finds a player by their ID.
     *
     * @param id the ID of the player to find
     * @return the player associated with the given ID, or null if not found
     */
    @Override
    public Player findByID(Long id) {
        return players.get(id);
    }

    /**
     * Retrieves all players in the repository.
     *
     * @return a map of all players, keyed by their IDs
     */
    @Override
    public Map<Long, Player> getAll() {
        return players;
    }

    /**
     * Gets a list of all players.
     *
     * @return a list containing all players
     */
    @Override
    public List<Player> getPlayers() {
        return players.values().stream().toList();
    }

    /**
     * Checks if a player with the specified ID exists in the repository.
     *
     * @param id the ID of the player to check
     * @return true if the player exists; false otherwise
     */
    @Override
    public boolean containsID(Long id) {
        return players.containsKey(id);
    }

    /**
     * Checks if the specified player exists in the repository.
     *
     * @param player the player to check
     * @return true if the player exists; false otherwise
     */
    @Override
    public boolean containsValue(Player player) {
        return players.containsValue(player);
    }

    /**
     * Adds a new player to the repository.
     *
     * @param player the player to add
     */
    @Override
    public void addPlayer(Player player) {
        players.put(PlayerMapper.toId.applyAsLong(player), player);
    }

    /**
     * Removes a player from the repository by their ID.
     *
     * @param id the ID of the player to remove
     */
    @Override
    public void removeByID(Long id) {
        players.remove(id);
    }
}