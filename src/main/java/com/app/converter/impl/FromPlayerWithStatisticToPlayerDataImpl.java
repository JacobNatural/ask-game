package com.app.converter.impl;

import com.app.converter.FromPlayerWithStatisticToPlayerData;
import com.app.model.player.PlayerData;
import com.app.repository.model.Player;
import com.app.repository.model.PlayerWithStatistic;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link FromPlayerWithStatisticToPlayerData} interface, responsible for converting
 * a list of {@link PlayerWithStatistic} entities into a list of {@link PlayerData}.
 */
@Component
public class FromPlayerWithStatisticToPlayerDataImpl
        implements FromPlayerWithStatisticToPlayerData {

    /**
     * Converts a list of {@link PlayerWithStatistic} entities into a list of {@link PlayerData}.
     * Each player's statistics (time and score) are aggregated into a map and then added to the player's data.
     *
     * @param entityType List of {@link PlayerWithStatistic} entities to convert.
     * @return List of {@link PlayerData} containing player details and their associated statistics.
     */
    public List<PlayerData> toList(List<PlayerWithStatistic> entityType) {
        return entityType
                .stream()
                .collect(Collectors.toMap(
                                // Create a Player object (with ID and login) as the map key
                                s -> new Player(s.getId(), s.getLogin()),

                                // Map the player's statistics (time -> score) as the map value
                                s -> new HashMap<>(Map.of(s.getStat_time(), s.getScore())),

                                // If multiple entries have the same key (player), merge their statistic maps
                                (v1, v2) -> {
                                    v1.putAll(v2);
                                    return v1;
                                }
                        )
                ).entrySet()
                .stream()
                .map(m ->
                        // Create a new PlayerData object using the Player and statistics map
                        new PlayerData(m.getKey().getId(), m.getKey().getLogin(), m.getValue()))
                .toList();
    }
}