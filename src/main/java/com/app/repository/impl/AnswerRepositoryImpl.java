package com.app.repository.impl;

import com.app.repository.AnswerRepository;
import com.app.repository.impl.generic.impl.AbstractCrudRepository;
import com.app.repository.model.Answer;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the AnswerRepository interface using JDBI for database operations.
 * Extends the AbstractCrudRepository to provide CRUD functionalities for Answer entities.
 */
@Repository
public class AnswerRepositoryImpl extends AbstractCrudRepository<Answer, Long> implements AnswerRepository {

    /**
     * Constructs an AnswerRepositoryImpl with the specified Jdbi instance.
     *
     * @param jdbi The Jdbi instance for executing database operations.
     */
    public AnswerRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    /**
     * Retrieves all answers associated with a specific quest ID.
     *
     * @param questId The ID of the quest for which answers are to be retrieved.
     * @return A list of answers linked to the specified quest ID.
     */
    public List<Answer> findByQuestId(Long questId) {
        var sql = "SELECT * FROM answers WHERE quest_id = :questId";

        return jdbi.withHandle(handle ->
                handle
                        .createQuery(sql)
                        .bind("questId", questId)
                        .mapToBean(Answer.class)
                        .list());
    }

    /**
     * Deletes all answers associated with a specific quest ID.
     *
     * @param questId The ID of the quest for which answers are to be deleted.
     * @return A list of answers that were deleted.
     */
    public List<Answer> deleteByQuestId(Long questId) {
        var sql = "DELETE FROM answers WHERE quest_id = :questId";

        // Retrieve the answers before deletion to return them
        var answers = findByQuestId(questId);

        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("questId", questId)
                        .execute());

        return answers;
    }
}