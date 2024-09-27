package com.app.game.service.impl;

import com.app.difficulty_level.DifficultyLevel;
import com.app.game.service.QuestCacheService;
import com.app.game.service.impl.generic.AbstractQuestCacheService;
import com.app.quest.QuestMapper;
import com.app.repository.QuestRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Service implementation for caching quests based on their difficulty levels.
 *
 * <p>This class extends {@link AbstractQuestCacheService} to provide caching functionality
 * specifically for quests grouped by their {@link DifficultyLevel}. It initializes the cache
 * with quests upon construction.
 */
@Service
public class QuestDifficultyLevelCacheServiceImpl extends AbstractQuestCacheService<DifficultyLevel>
        implements QuestCacheService<DifficultyLevel> {

    /**
     * Constructor that initializes the quest repository.
     *
     * @param questRepositoryImpl the repository used to fetch quests from the data source
     */
    public QuestDifficultyLevelCacheServiceImpl(QuestRepository<Long> questRepositoryImpl) {
        super(questRepositoryImpl);
    }

    /**
     * Post-construct method that refreshes the quest cache with quests grouped by difficulty level.
     * This method is called after the bean is constructed and the dependencies are injected.
     */
    @PostConstruct
    public void postConstruct() {
        refresh(QuestMapper.toDifficulty);
    }
}