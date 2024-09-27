package com.app.txt.model;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Represents data for a player, including their login and statistics.
 *
 * @param login      the player's login identifier
 * @param statistics a map of timestamps and corresponding scores for the player
 */
public record PlayerData(String login, Map<LocalDateTime, Integer> statistics) {

    /**
     * Updates the player's statistics with new values.
     * <p>
     * Existing statistics will remain unchanged if new values conflict.
     *
     * @param statistics a map of timestamps and scores to update
     */
    public void updateStatistic(Map<LocalDateTime, Integer> statistics) {
        statistics.forEach((key, value) -> this.statistics.merge(
                key,
                value,
                (v1, v2) -> v1));
    }
}