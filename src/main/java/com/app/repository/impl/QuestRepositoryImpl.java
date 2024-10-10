package com.app.repository.impl;

import com.app.repository.QuestRepository;
import com.app.repository.impl.generic.impl.AbstractCrudRepository;
import com.app.repository.model.Quest;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the QuestRepository interface for managing quests.
 * This class provides CRUD operations for quests using JDBI.
 */
@Repository
public class QuestRepositoryImpl extends AbstractCrudRepository<Quest, Long> implements QuestRepository {

    /**
     * Constructs a QuestRepositoryImpl with the specified JDBI instance.
     *
     * @param jdbi The JDBI instance to be used for database operations.
     */
    public QuestRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }
}