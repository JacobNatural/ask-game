package com.app.player;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToLongFunction;

/**
 * Interface for mapping {@link Player} objects to different representations.
 *
 * <p>This interface provides functions to extract the login, statistics,
 * and ID from a {@code Player} instance.</p>
 */
public interface PlayerMapper {

    /**
     * Function to extract the login from a Player.
     */
    Function<Player, String> toLogin = player -> player.login;

    /**
     * Function to extract statistics from a Player.
     * This function takes a {@link Player} and returns a map of statistics
     * where the key is a {@link LocalDateTime} and the value is an Integer.
     */
    Function<Player, Map<LocalDateTime, Integer>> toStatistic = player -> player.statistics;

    /**
     * Function to extract the ID from a Player.
     * This function takes a {@link Player} and returns its ID as a long.
     */
    ToLongFunction<Player> toId = player -> player.id;
}