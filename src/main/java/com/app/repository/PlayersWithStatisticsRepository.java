package com.app.repository;

import com.app.repository.model.PlayerWithStatistic;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for managing PlayerWithStatistic entities.
 * This interface provides methods to retrieve player statistics
 * and determine top-performing players.
 */
public interface PlayersWithStatisticsRepository {

    /**
     * Retrieves the statistics for a specific player identified by their ID.
     *
     * @param id the ID of the player whose statistics are to be retrieved
     * @return a List of PlayerWithStatistic objects containing the player's statistics
     */
    List<PlayerWithStatistic> findPlayerWithStatistic(Long id);

    /**
     * Retrieves the statistics for all players.
     *
     * @return a List of PlayerWithStatistic objects for all players
     */
    List<PlayerWithStatistic> findPlayersWithStatistics();

    /**
     * Retrieves the best-performing player based on their statistics.
     *
     * @return a List of PlayerWithStatistic objects for the best player(s)
     */
    List<PlayerWithStatistic> getTheBestPlayer();

    /**
     * Retrieves the best-performing player(s) within a specified time period.
     *
     * @param from the start date and time of the period
     * @param to the end date and time of the period
     * @return a List of PlayerWithStatistic objects for the best player(s) in the given period
     */
    List<PlayerWithStatistic> getTheBestPlayerInPeriod(LocalDateTime from, LocalDateTime to);
}
