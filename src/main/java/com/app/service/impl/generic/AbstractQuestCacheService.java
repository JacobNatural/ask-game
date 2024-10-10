package com.app.service.impl.generic;

import com.app.converter.FromQuestWithAnswerToQuestData;
import com.app.repository.QuestWithAnswerRepository;
import com.app.repository.model.Quest;
import com.app.service.QuestCacheService;
import com.app.model.quest.QuestData;

import java.util.*;
import java.util.function.Function;

/**
 * Abstract class providing caching functionality for quests.
 * This class handles the retrieval and storage of quest data
 * in a map, allowing for efficient access and manipulation of
 * quest-related information.
 *
 * @param <T> the type used as a key in the quest cache
 */
public abstract class AbstractQuestCacheService<T> implements QuestCacheService<T> {

    protected final QuestWithAnswerRepository questWithAnswerRepository;
    protected final FromQuestWithAnswerToQuestData fromQuestWithAnswerToQuestData;

    protected Map<T, List<QuestData>> quests;

    /**
     * Constructs a new instance of AbstractQuestCacheService.
     *
     * @param questWithAnswerRepository the repository used to retrieve quests with answers
     * @param fromQuestWithAnswerToQuestData the converter used to transform quests into quest data
     */
    protected AbstractQuestCacheService(QuestWithAnswerRepository questWithAnswerRepository,
                                        FromQuestWithAnswerToQuestData fromQuestWithAnswerToQuestData) {
        this.questWithAnswerRepository = questWithAnswerRepository;
        this.fromQuestWithAnswerToQuestData = fromQuestWithAnswerToQuestData;
        this.quests = null;
    }

    /**
     * Retrieves all quests in the cache.
     *
     * @return a map of quests with their corresponding quest data
     */
    @Override
    public Map<T, List<QuestData>> getAll() {
        return quests;
    }

    /**
     * Refreshes the cached quest data using the provided mapping function.
     *
     * @param map a function that maps a Quest to the type T used as a key
     * @throws IllegalArgumentException if the provided mapping function is null
     */
    @Override
    public void refresh(Function<Quest, T> map) {
        if (map == null) {
            throw new IllegalArgumentException("Mapper cannot be null");
        }

        quests = fromQuestWithAnswerToQuestData
                .toGroupBy(questWithAnswerRepository.findQuestsWithAnswers(), map);
    }
}
