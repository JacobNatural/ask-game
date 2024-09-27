package com.app.game.service;

import com.app.player.Player;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Service interface for managing player-related operations.
 *
 * <p>This interface defines methods for adding, removing, finding players,
 * saving player data, and retrieving player rankings.</p>
 */
public interface PlayerService {

    /**
     * Adds a new player to the service.
     *
     * @param player the {@link Player} object to be added
     * @throws IllegalArgumentException if the player is {@code null} or already exists
     */
    void addPlayer(Player player);

    /**
     * Removes a player from the service by their unique ID.
     *
     * @param id the unique ID of the player to be removed
     * @throws IllegalArgumentException if the player with the specified ID is not found
     */
    void removePlayer(Long id);

    /**
     * Saves the current list of players to a text file using a specified format.
     *
     * @param filename    the name of the file where player data will be saved
     * @param toTxtFormat a function that converts a {@link Player} object to a text format
     * @throws IllegalArgumentException if the {@code toTxtFormat} function is {@code null}
     */
    void savePlayerToTxt(String filename, Function<Player, String> toTxtFormat);

    /**
     * Finds a player by their unique ID.
     *
     * @param playerId the unique ID of the player
     * @return the {@link Player} object if found
     * @throws IllegalArgumentException if the player is not found
     */
    Player findPlayer(Long playerId);

    /**
     * Retrieves a list of the best players based on their scores.
     *
     * @return a list of the best {@link Player} objects
     */
    List<Player> getTheBestPlayer();

    /**
     * Retrieves a list of the best players within a specified period.
     *
     * @param from the start date of the period
     * @param to   the end date of the period
     * @return a list of the best {@link Player} objects within the specified period
     * @throws IllegalArgumentException if the start date is after the end date or if dates are {@code null}
     */
    List<Player> getTheBestPlayerInPeriod(LocalDateTime from, LocalDateTime to);

    /**
     * Retrieves the ranking of players based on their scores.
     *
     * @return a map where the key is the score and the value is a set of player usernames with that score
     */
    Map<Integer, Set<String>> getRankingOfPlayers();

    /**
     * Checks if a player with the specified ID exists.
     *
     * @param ID the unique ID of the player
     * @return {@code true} if the player exists, {@code false} otherwise
     */
    boolean containID(Long ID);
}