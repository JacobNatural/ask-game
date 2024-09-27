package com.app.game.service.impl.generic;

import com.app.game.service.QuestCacheService;
import com.app.quest.Quest;
import com.app.repository.QuestRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Abstract class providing common functionality for caching quests in the game.
 *
 * <p>This class implements the {@link QuestCacheService} interface and provides methods for
 * retrieving and refreshing cached quests. The quests are stored in a map, where the key is
 * a generic type {@code T} (determined by subclasses), and the value is a list of {@link Quest} objects.
 *
 * @param <T> the type of key used to group quests in the cache (e.g., by difficulty level, category, etc.)
 */
public abstract class AbstractQuestCacheService<T> implements QuestCacheService<T> {

    /**
     * Repository for retrieving quests from the data source.
     */
    protected final QuestRepository<Long> questRepository;

    /**
     * Cache storing the quests grouped by the generic type {@code T}.
     */
    protected Map<T, List<Quest>> quests;

    /**
     * Constructor that initializes the quest repository and sets the cache to {@code null}.
     *
     * @param questRepository the repository used to fetch quests from the data source
     */
    protected AbstractQuestCacheService(QuestRepository<Long> questRepository) {
        this.questRepository = questRepository;
        this.quests = null;
    }

    /**
     * Retrieves all cached quests.
     *
     * @return a map where the key is of type {@code T} and the value is a list of quests.
     */
    @Override
    public Map<T, List<Quest>> getAll() {
        return quests;
    }

    /**
     * Refreshes the quest cache by grouping quests based on the provided mapping function.
     *
     * <p>The {@code map} function is used to determine the grouping key (of type {@code T}) for each quest.
     *
     * @param map a function that maps a {@link Quest} to a key of type {@code T} used for grouping
     * @throws IllegalArgumentException if the {@code map} function is {@code null}
     */
    @Override
    public void refresh(Function<Quest, T> map) {

        if (map == null) {
            throw new IllegalArgumentException("Mapper cannot be null");
        }

        quests = questRepository
                .getQuests()
                .stream()
                .collect(Collectors.groupingBy(map));
    }
}