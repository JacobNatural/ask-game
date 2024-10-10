package com.app.service.impl;

import com.app.converter.FromPlayerWithStatisticToPlayerData;
import com.app.dto.PlayerDto;
import com.app.repository.PlayersWithStatisticsRepository;
import com.app.service.PlayerService;
import com.app.model.player.PlayerData;
import com.app.repository.PlayerRepository;
import com.app.repository.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the PlayerService interface, providing methods for managing players,
 * including adding, removing, and retrieving player statistics.
 */
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayersWithStatisticsRepository playersWithStatisticsRepository;
    private final FromPlayerWithStatisticToPlayerData fromPlayerWithStatisticToPlayerDataImpl;

    /**
     * Adds a new player using the provided PlayerDto.
     *
     * @param playerData the data of the player to be added
     * @return PlayerData representing the added player
     * @throws IllegalArgumentException if playerData is null
     */
    @Override
    public PlayerData addPlayer(PlayerDto playerData) {
        if (playerData == null) {
            throw new IllegalArgumentException("PlayerDto cannot be null");
        }

        return PlayerData.fromPlayer(
                playerRepository.save(new Player(playerData.id(), playerData.login()))
        );
    }

    /**
     * Removes a player by their ID.
     *
     * @param id the ID of the player to be removed
     * @return PlayerData representing the removed player
     */
    @Override
    public PlayerData removePlayer(Long id) {
        return PlayerData.fromPlayer(playerRepository.delete(id));
    }

    /**
     * Finds a player by their ID and returns their data.
     *
     * @param playerId the ID of the player to find
     * @return PlayerData representing the found player
     */
    @Override
    public PlayerData findPlayer(Long playerId) {
        return fromPlayerWithStatisticToPlayerDataImpl
                .toList(playersWithStatisticsRepository.findPlayerWithStatistic(playerId))
                .getFirst();
    }

    /**
     * Retrieves the best player based on statistics.
     *
     * @return a list of PlayerData representing the best players
     */
    @Override
    public List<PlayerData> getTheBestPlayer() {
        return fromPlayerWithStatisticToPlayerDataImpl
                .toList(playersWithStatisticsRepository.getTheBestPlayer());
    }

    /**
     * Retrieves the best players within a specified time period.
     *
     * @param from the start date of the period
     * @param to the end date of the period
     * @return a list of PlayerData representing the best players in the period
     * @throws IllegalArgumentException if from or to is null or if from is after to
     */
    @Override
    public List<PlayerData> getTheBestPlayerInPeriod(LocalDateTime from, LocalDateTime to) {
        if (from == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }

        if (to == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }

        if (from.isAfter(to)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        return fromPlayerWithStatisticToPlayerDataImpl.toList(playersWithStatisticsRepository
                .getTheBestPlayerInPeriod(from, to));
    }

    /**
     * Retrieves the ranking of players based on their scores.
     *
     * @return a map of player rankings where the key is the score and the value is the player's login
     */
    @Override
    public Map<Integer, String> getRankingOfPlayers() {
        return playerRepository.getPlayersRanking();
    }
}
