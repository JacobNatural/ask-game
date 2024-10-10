package com.app.repository;

import com.app.repository.model.QuestWithAnswer;

import java.util.List;

/**
 * Repository interface for managing QuestWithAnswer entities.
 * This interface provides methods to retrieve quest data along
 * with their corresponding answers.
 */
public interface QuestWithAnswerRepository {

    /**
     * Finds a specific quest along with its answers by quest ID.
     *
     * @param id the ID of the quest to find
     * @return a list of QuestWithAnswer objects associated with the specified quest ID
     */
    List<QuestWithAnswer> findQuestWithAnswer(Long id);

    /**
     * Retrieves all quests along with their corresponding answers.
     *
     * @return a list of all QuestWithAnswer objects
     */
    List<QuestWithAnswer> findQuestsWithAnswers();

    /**
     * Finds quests along with their answers by a list of quest IDs.
     *
     * @param ids a list of quest IDs to find
     * @return a list of QuestWithAnswer objects associated with the specified quest IDs
     */
    List<QuestWithAnswer> findQuestsWithAnswersByIds(List<Long> ids);
}
