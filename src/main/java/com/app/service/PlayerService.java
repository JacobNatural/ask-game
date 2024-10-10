package com.app.service;

import com.app.dto.PlayerDto;
import com.app.model.player.PlayerData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Interface representing the player service, providing functionalities
 * for managing player data and statistics.
 */
public interface PlayerService {

    /**
     * Adds a new player to the system.
     *
     * @param playerData the PlayerDto object containing the player's details.
     * @return a PlayerData object representing the added player.
     * @throws IllegalArgumentException if the provided playerData is null.
     */
    PlayerData addPlayer(PlayerDto playerData);

    /**
     * Removes a player from the system by their ID.
     *
     * @param id the ID of the player to be removed.
     * @return a PlayerData object representing the removed player.
     */
    PlayerData removePlayer(Long id);

    /**
     * Finds a player by their ID.
     *
     * @param playerId the ID of the player to be found.
     * @return a PlayerData object representing the found player.
     */
    PlayerData findPlayer(Long playerId);

    /**
     * Retrieves a list of the best players based on their scores.
     *
     * @return a list of PlayerData objects representing the best players.
     */
    List<PlayerData> getTheBestPlayer();

    /**
     * Retrieves a list of the best players within a specified time period.
     *
     * @param from the start date and time of the period.
     * @param to   the end date and time of the period.
     * @return a list of PlayerData objects representing the best players in the specified period.
     * @throws IllegalArgumentException if the start date is after the end date or if any date is null.
     */
    List<PlayerData> getTheBestPlayerInPeriod(LocalDateTime from, LocalDateTime to);

    /**
     * Retrieves the ranking of players.
     *
     * @return a Map where the key is the player's rank and the value is the player's login.
     */
    Map<Integer, String> getRankingOfPlayers();
}
