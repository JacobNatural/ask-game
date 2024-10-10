package com.app.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a player's statistic record.
 * This class encapsulates the score and the time at which the score was recorded
 * for a specific player.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {

    /**
     * The unique identifier for the statistic record.
     */
    private Long id;

    /**
     * The unique identifier of the player to whom the statistic belongs.
     */
    private Long playerId;

    /**
     * The timestamp indicating when the statistic was recorded.
     */
    private LocalDateTime stat_time;

    /**
     * The score achieved by the player at the recorded time.
     */
    private int score;
}