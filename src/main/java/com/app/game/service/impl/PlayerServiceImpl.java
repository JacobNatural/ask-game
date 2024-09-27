package com.app.game.service.impl;

import com.app.game.service.PlayerService;
import com.app.player.Player;
import com.app.player.PlayerMapper;
import com.app.repository.PlayerRepository;
import com.app.txt.save.FileWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service implementation for managing player-related operations.
 *
 * <p>This class provides methods for adding, removing, finding, and saving players,
 * as well as generating rankings and filtering players by specific periods.
 */
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository<Long> playerRepository;
    private final FileWriter<Player> playerFileWriter;

    /**
     * Adds a new player to the repository.
     *
     * @param player the {@link Player} object to be added
     * @throws IllegalArgumentException if the player is {@code null} or already exists in the repository
     */
    @Override
    public void addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }

        if (playerRepository.containsValue(player)) {
            throw new IllegalArgumentException("Player already exists");
        }

        playerRepository.addPlayer(player);
    }

    /**
     * Removes a player from the repository by ID.
     *
     * @param id the unique ID of the player to be removed
     * @throws IllegalArgumentException if the player with the specified ID is not found
     */
    @Override
    public void removePlayer(Long id) {
        if (!playerRepository.containsID(id)) {
            throw new IllegalArgumentException("Player not found");
        }
        playerRepository.removeByID(id);
    }

    /**
     * Saves the current list of players to a text file using a specified format.
     *
     * @param filename    the name of the file where players will be saved
     * @param toTxtFormat a function that converts a {@link Player} object to a text format
     * @throws IllegalArgumentException if the {@code toTxtFormat} function is {@code null}
     */
    @Override
    public void savePlayerToTxt(String filename, Function<Player, String> toTxtFormat) {
        if (toTxtFormat == null) {
            throw new IllegalArgumentException("ToTxtFormat cannot be null");
        }
        playerFileWriter.save(filename, playerRepository.getPlayers(), toTxtFormat);
    }

    /**
     * Finds a player by their unique ID.
     *
     * @param playerId the unique ID of the player
     * @return the {@link Player} object if found
     * @throws IllegalArgumentException if the player is not found
     */
    @Override
    public Player findPlayer(Long playerId) {
        if (!playerRepository.containsID(playerId)) {
            throw new IllegalArgumentException("Player not found");
        }
        return playerRepository.findByID(playerId);
    }

    /**
     * Retrieves the player(s) with the best score from the repository.
     *
     * @return a list of {@link Player} objects with the highest score
     */
    @Override
    public List<Player> getTheBestPlayer() {
        return getTheBestPlayer(playerRepository.getPlayers());
    }

    /**
     * Checks if the repository contains a player with the given ID.
     *
     * @param playerID the unique ID of the player
     * @return {@code true} if the player exists, {@code false} otherwise
     */
    @Override
    public boolean containID(Long playerID) {
        return playerRepository.containsID(playerID);
    }

    /**
     * Retrieves the best player(s) within a specific time period.
     *
     * @param from the start date of the period
     * @param to   the end date of the period
     * @return a list of {@link Player} objects with the highest score in the specified period
     * @throws IllegalArgumentException if {@code from} or {@code to} is {@code null}, or if {@code from} is after {@code to}
     */
    @Override
    public List<Player> getTheBestPlayerInPeriod(LocalDateTime from, LocalDateTime to) {
        if (from == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }

        if (to == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }

        if (from.isAfter(to)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        var playersInPeriod = getPlayersFilterByPeriod(from, to);

        return getTheBestPlayer(playersInPeriod);
    }

    /**
     * Generates a ranking of players based on their best scores.
     *
     * @return a map where the key is the score and the value is a set of player logins, sorted in descending order by score
     */
    @Override
    public Map<Integer, Set<String>> getRankingOfPlayers() {
        return playerRepository
                .getPlayers()
                .stream()
                .collect(Collectors.groupingBy(Player::getTheBestScore,
                        Collectors.mapping(PlayerMapper.toLogin, Collectors.toSet())))
                .entrySet()
                .stream()
                .sorted((m1, m2) -> Integer.compare(m2.getKey(), m1.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1, v2) -> v1, LinkedHashMap::new));
    }

    /**
     * Retrieves the player(s) with the best score from the given list of players.
     *
     * @param players the list of {@link Player} objects to evaluate
     * @return a list of {@link Player} objects with the highest score
     * @throws IllegalArgumentException if the list of players is empty
     */
    private static List<Player> getTheBestPlayer(List<Player> players) {
        return players
                .stream()
                .collect(Collectors.groupingBy(Player::getTheBestScore))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalArgumentException("Repository is empty"));
    }

    /**
     * Filters players by a given time period.
     *
     * @param from the start date of the period
     * @param to   the end date of the period
     * @return a list of {@link Player} objects whose activities fall within the specified period
     */
    private List<Player> getPlayersFilterByPeriod(LocalDateTime from, LocalDateTime to) {
        return playerRepository
                .getPlayers()
                .stream()
                .map(player -> player.filterByPeriod(from, to))
                .filter(Objects::nonNull)
                .toList();
    }
}
