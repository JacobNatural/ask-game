package com.app.service.impl;

import com.app.converter.FromQuestWithAnswerToQuestData;
import com.app.model.difficulty_level.DifficultyLevel;
import com.app.repository.QuestWithAnswerRepository;
import com.app.repository.model.Quest;
import com.app.service.QuestCacheService;
import com.app.service.impl.generic.AbstractQuestCacheService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Implementation of QuestCacheService for caching quests based on their difficulty levels.
 * This service retrieves quests from the repository and groups them by their difficulty level.
 */
@Service
public class QuestDifficultyLevelCacheServiceImpl extends AbstractQuestCacheService<DifficultyLevel>
        implements QuestCacheService<DifficultyLevel> {

    /**
     * Constructs an instance of QuestDifficultyLevelCacheServiceImpl.
     *
     * @param questWithAnswerRepository the repository for fetching quests and their answers
     * @param fromQuestWithAnswerToQuestData the converter that maps QuestWithAnswer to QuestData
     */
    public QuestDifficultyLevelCacheServiceImpl(
            QuestWithAnswerRepository questWithAnswerRepository,
            FromQuestWithAnswerToQuestData fromQuestWithAnswerToQuestData) {
        super(questWithAnswerRepository, fromQuestWithAnswerToQuestData);
    }

    /**
     * Initializes the cache with quests grouped by their difficulty level.
     * This method is called automatically after the bean's properties have been set.
     */
    @PostConstruct
    public void postConstruct() {
        refresh(Quest::getDifficultyLevel);
    }
}
