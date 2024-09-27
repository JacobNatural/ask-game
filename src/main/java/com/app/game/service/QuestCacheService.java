package com.app.game.service;

import com.app.quest.Quest;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Service interface for caching quests based on a specified key type.
 *
 * <p>This interface defines methods for retrieving all cached quests and refreshing
 * the cache using a mapping function.</p>
 *
 * @param <T> the type of key used for caching quests
 */
public interface QuestCacheService<T> {

    /**
     * Retrieves all cached quests grouped by their associated keys.
     *
     * @return a map where the key is of type {@code T} and the value is a list of {@link Quest} objects
     */
    Map<T, List<Quest>> getAll();

    /**
     * Refreshes the quest cache using the provided mapping function.
     *
     * @param map a function that maps a {@link Quest} object to a key of type {@code T}
     * @throws IllegalArgumentException if the mapping function is {@code null}
     */
    void refresh(Function<Quest, T> map);
}