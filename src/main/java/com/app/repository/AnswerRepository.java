package com.app.repository;

import com.app.repository.impl.generic.CrudRepository;
import com.app.repository.model.Answer;

import java.util.List;

/**
 * Repository interface for managing Answer entities.
 * Extends the generic CrudRepository for basic CRUD operations
 * and provides additional methods specific to Answer operations.
 */
public interface AnswerRepository extends CrudRepository<Answer, Long> {

    /**
     * Retrieves a list of answers associated with a specific quest ID.
     *
     * @param questId the ID of the quest for which to find answers
     * @return a list of Answer objects related to the specified quest
     */
    List<Answer> findByQuestId(Long questId);

    /**
     * Deletes all answers associated with a specific quest ID.
     *
     * @param questId the ID of the quest whose answers are to be deleted
     * @return a list of Answer objects that were deleted
     */
    List<Answer> deleteByQuestId(Long questId);
}