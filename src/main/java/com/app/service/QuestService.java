package com.app.service;

import com.app.dto.AnswersDto;
import com.app.model.quest.QuestData;

import java.util.List;

/**
 * Interface for managing quests in the application.
 */
public interface QuestService {

    /**
     * Adds a new quest to the system.
     *
     * @param questData the data of the quest to be added
     * @return the added QuestData object with its generated ID
     * @throws IllegalArgumentException if questData is null
     */
    QuestData addQuest(QuestData questData);

    /**
     * Generates a list of random quests for gameplay.
     *
     * @return a list of randomly selected QuestData objects
     */
    List<QuestData> generateRandomsQuests();

    /**
     * Calculates the total points based on the provided answers.
     *
     * @param answersDto the answers submitted by the player
     * @return the total points calculated from the answers
     * @throws IllegalArgumentException if answersDto is null
     */
    int calculatePoints(AnswersDto answersDto);

    /**
     * Removes a quest from the system based on its ID.
     *
     * @param questID the ID of the quest to be removed
     * @return the removed QuestData object
     * @throws IllegalArgumentException if questID is null or if no quest is found with the given ID
     */
    QuestData removeQuest(Long questID);

    /**
     * Finds a quest by its ID.
     *
     * @param questID the ID of the quest to be found
     * @return the QuestData object corresponding to the given ID
     * @throws IllegalArgumentException if questID is null or if no quest is found with the given ID
     */
    QuestData findQuest(Long questID);
}
