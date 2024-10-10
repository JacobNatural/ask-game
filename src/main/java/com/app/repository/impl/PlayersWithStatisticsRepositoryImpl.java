package com.app.repository.impl;

import com.app.repository.PlayersWithStatisticsRepository;
import com.app.repository.model.PlayerWithStatistic;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the PlayersWithStatisticsRepository interface for managing player statistics.
 * Uses JDBI for database operations.
 */
@Repository
@RequiredArgsConstructor
public class PlayersWithStatisticsRepositoryImpl implements PlayersWithStatisticsRepository {
    private final Jdbi jdbi;

    /**
     * Retrieves the statistics for a specific player by their ID.
     *
     * @param id The ID of the player whose statistics are to be retrieved.
     * @return A list of PlayerWithStatistic objects containing the player's statistics.
     */
    public List<PlayerWithStatistic> findPlayerWithStatistic(Long id) {
        var sql = """
                SELECT p.id, p.login, s.stat_time, s.score
                FROM players p 
                JOIN statistics s ON p.id = s.player_id 
                WHERE p.id = :id;
                """;

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .mapToBean(PlayerWithStatistic.class)
                        .list());
    }

    /**
     * Retrieves all players along with their statistics.
     *
     * @return A list of PlayerWithStatistic objects for all players.
     */
    public List<PlayerWithStatistic> findPlayersWithStatistics() {
        var sql = """
                SELECT p.id, p.login, s.stat_time, s.score
                FROM players p 
                JOIN statistics s ON p.id = s.player_id;
                """;

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(PlayerWithStatistic.class)
                        .list());
    }

    /**
     * Retrieves the best player based on the highest score.
     *
     * @return A list of PlayerWithStatistic objects for the player(s) with the highest score.
     */
    public List<PlayerWithStatistic> getTheBestPlayer() {
        var sql = """
                SELECT p.id, p.login, s.stat_time, s.score 
                FROM players p 
                JOIN statistics s ON p.id = s.player_id 
                WHERE s.score = (SELECT MAX(score) FROM statistics);
                """;

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .mapToBean(PlayerWithStatistic.class)
                        .list());
    }

    /**
     * Retrieves the best player within a specified time period based on the highest score.
     *
     * @param from The start date and time of the period.
     * @param to   The end date and time of the period.
     * @return A list of PlayerWithStatistic objects for the player(s) with the highest score in the specified period.
     */
    public List<PlayerWithStatistic> getTheBestPlayerInPeriod(LocalDateTime from, LocalDateTime to) {
        var sql = """
                SELECT p.id, p.login, s.stat_time, s.score 
                FROM players p 
                JOIN statistics s ON p.id = s.player_id 
                WHERE s.stat_time BETWEEN :from AND :to 
                AND s.score = (SELECT MAX(score) 
                               FROM statistics s2 
                               WHERE s2.stat_time BETWEEN :from AND :to);
                """;

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("from", from)
                        .bind("to", to)
                        .mapToBean(PlayerWithStatistic.class)
                        .list());
    }
}
