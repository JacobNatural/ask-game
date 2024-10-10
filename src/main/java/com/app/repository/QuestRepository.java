package com.app.repository;

import com.app.repository.impl.generic.CrudRepository;
import com.app.repository.model.Quest;

/**
 * Repository interface for managing Quest entities.
 * This interface extends the CrudRepository to provide
 * standard CRUD operations for Quest objects.
 */
public interface QuestRepository extends CrudRepository<Quest, Long> {
    // No additional methods defined, as it inherits from CrudRepository
}
