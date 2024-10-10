package com.app.service;

import com.app.model.quest.QuestData;
import com.app.repository.model.Quest;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Interface for a quest cache service that manages cached quests
 * grouped by a specified key type.
 *
 * @param <T> the type of the key used to group quests
 */
public interface QuestCacheService<T> {

    /**
     * Retrieves all cached quests grouped by their key.
     *
     * @return a Map where the key is of type T and the value is a list of QuestData objects
     * representing the quests associated with that key.
     */
    Map<T, List<QuestData>> getAll();

    /**
     * Refreshes the cached quests by mapping each quest to a new key.
     *
     * @param map a function that defines how to map a Quest to a key of type T
     * @throws IllegalArgumentException if the provided mapping function is null
     */
    void refresh(Function<Quest, T> map);
}