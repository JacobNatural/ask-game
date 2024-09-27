package com.app.repository;

import com.app.player.Player;

import java.util.List;
import java.util.Map;

/**
 * Interface for managing player data in a repository.
 *
 * <p>This interface defines the operations available for interacting with player records,
 * including adding, removing, and retrieving players.</p>
 *
 * @param <T> the type of the identifier used for players
 */
public interface PlayerRepository<T> {

    /**
     * Retrieves all players in the repository.
     *
     * @return a map of all players, keyed by their identifiers
     */
    Map<T, Player> getAll();

    /**
     * Gets a list of all players.
     *
     * @return a list containing all players
     */
    List<Player> getPlayers();

    /**
     * Adds a new player to the repository.
     *
     * @param p the player to add
     */
    void addPlayer(Player p);

    /**
     * Checks if a player with the specified ID exists in the repository.
     *
     * @param id the ID of the player to check
     * @return true if the player exists; false otherwise
     */
    boolean containsID(Long id);

    /**
     * Checks if the specified player exists in the repository.
     *
     * @param player the player to check
     * @return true if the player exists; false otherwise
     */
    boolean containsValue(Player player);

    /**
     * Finds a player by their ID.
     *
     * @param id the ID of the player to find
     * @return the player associated with the given ID, or null if not found
     */
    Player findByID(Long id);

    /**
     * Removes a player from the repository by their ID.
     *
     * @param id the ID of the player to remove
     */
    void removeByID(Long id);
}
