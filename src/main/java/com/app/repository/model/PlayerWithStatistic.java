package com.app.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a player along with their associated statistics.
 * This class contains the player's unique identifier, login information,
 * the time of the statistic recorded, and the score achieved by the player.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerWithStatistic {

    /**
     * The unique identifier for the player.
     */
    private long id;

    /**
     * The login name of the player.
     * This is used for authentication and identification purposes.
     */
    private String login;

    /**
     * The timestamp when the statistic was recorded.
     */
    private LocalDateTime stat_time;

    /**
     * The score achieved by the player.
     * This represents the performance of the player in a specific context.
     */
    private int score;
}