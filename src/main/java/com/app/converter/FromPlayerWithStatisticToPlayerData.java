package com.app.converter;

import com.app.model.player.PlayerData;
import com.app.repository.model.PlayerWithStatistic;

/**
 * Interface for converting a list of {@link PlayerWithStatistic} objects into {@link PlayerData} objects.
 * This interface extends the {@link Converter} interface, specifying the source and target types.
 */
public interface FromPlayerWithStatisticToPlayerData extends Converter<PlayerWithStatistic, PlayerData> {
    // No additional methods are defined; this interface serves as a specific type of Converter.
}