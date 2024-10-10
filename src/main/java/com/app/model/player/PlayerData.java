package com.app.model.player;

import com.app.repository.model.Player;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a player's data, including their statistics and identification details.
 * <p>
 * This class contains the player's ID, login, and a map of statistics that tracks their scores
 * associated with specific timestamps.
 * </p>
 */
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"login"})
@ToString
public class PlayerData {
    final Long id;
    final String login;
    final Map<LocalDateTime, Integer> statistics;

    /**
     * Retrieves the highest score from the player's statistics.
     *
     * @return The highest score as an integer.
     */
    public int getTheBestScore() {
        return statistics
                .values()
                .stream()
                .max(Comparator.naturalOrder())
                .orElseThrow();
    }

    /**
     * Creates a {@link PlayerData} instance from a {@link Player} object.
     *
     * @param player The player object to create the {@link PlayerData} from.
     * @return A new {@link PlayerData} instance initialized with the player's ID and login,
     *         and an empty statistics map.
     */
    public static PlayerData fromPlayer(Player player) {
        return new PlayerData(player.getId(), player.getLogin(), new HashMap<>());
    }
}