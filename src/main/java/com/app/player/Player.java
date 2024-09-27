package com.app.player;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a player with a unique ID, login, and statistics.
 *
 * <p>This class provides methods for updating statistics, retrieving the best score,
 * and filtering statistics by a specified time period.</p>
 */
@AllArgsConstructor
@EqualsAndHashCode(of = {"login"})
@ToString
public class Player {
    final Long id;
    final String login;
    final Map<LocalDateTime, Integer> statistics;

    static Long idCounter = 1L;

    /**
     * Constructs a new Player with a generated ID.
     *
     * @param login      the player's login
     * @param statistics a map of the player's statistics, keyed by date
     */
    public Player(String login, Map<LocalDateTime, Integer> statistics) {
        this.id = idCounter++;
        this.login = login;
        this.statistics = statistics;
    }

    /**
     * Updates the player's statistics with new values.
     *
     * <p>If a date already exists, the existing value is retained.</p>
     *
     * @param statistics a map of statistics to update
     */
    public void updateStatistic(Map<LocalDateTime, Integer> statistics) {
        statistics.forEach((key, value) -> this.statistics.merge(
                key,
                value,
                (v1, v2) -> v1));
    }

    /**
     * Retrieves the player's best score.
     *
     * @return the highest score from the player's statistics
     */
    public int getTheBestScore() {
        return statistics
                .values()
                .stream()
                .max(Comparator.naturalOrder())
                .orElseThrow();
    }

    /**
     * Filters the player's statistics by a specified period.
     *
     * @param start the start of the period
     * @param end   the end of the period
     * @return a new Player object with statistics from the specified period,
     * or null if no statistics fall within the period
     */
    public Player filterByPeriod(LocalDateTime start, LocalDateTime end) {
        var periodStatistic = this.statistics
                .entrySet()
                .stream()
                .filter(m -> inPeriod(m.getKey(), start, end))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return periodStatistic.isEmpty() ? null : new Player(login, periodStatistic);
    }

    /**
     * Checks if a given date is within the specified period.
     *
     * @param date the date to check
     * @param from the start of the period
     * @param to   the end of the period
     * @return true if the date is within the period, false otherwise
     */
    private static boolean inPeriod(LocalDateTime date, LocalDateTime from, LocalDateTime to) {
        return date.isAfter(from) && date.isBefore(to);
    }

    /**
     * Converts a Player object to a formatted string representation for text output.
     *
     * @param player the player to format
     * @return a string representation of the player's login and statistics
     */
    public static String toTxtFormat(Player player) {
        var sb = new StringBuilder();

        var statistic = player.statistics
                .entrySet()
                .stream()
                .map(map -> String.format("%s;%d", map.getKey(), map.getValue()))
                .collect(Collectors.joining(";"));
        sb.append(player.login)
                .append(";")
                .append(statistic);
        return sb.toString();
    }
}