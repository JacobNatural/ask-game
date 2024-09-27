package com.app.game.service;

import com.app.dto.AnswersDto;
import com.app.quest.Quest;

import java.util.List;
import java.util.function.Function;

/**
 * Service interface for managing quests in the game.
 *
 * <p>This interface defines methods for adding, saving, generating random quests,
 * calculating points based on answers, and finding quests.</p>
 *
 * @param <T> the type representing the difficulty level or other quest-related categorization
 */
public interface QuestService<T> {

    /**
     * Adds a new quest to the service.
     *
     * @param quest the {@link Quest} object to be added
     * @throws IllegalArgumentException if the quest is {@code null} or already exists
     */
    void addQuest(Quest quest);

    /**
     * Saves the current list of quests to a text file using a specified format.
     *
     * @param filename    the name of the file where quests will be saved
     * @param toTxtFormat a function that converts a {@link Quest} object to a text format
     * @throws IllegalArgumentException if the {@code toTxtFormat} function is {@code null}
     */
    void saveQuestToTxt(String filename, Function<Quest, String> toTxtFormat);

    /**
     * Generates a list of random quests for the game.
     *
     * @return a list of randomly selected {@link Quest} objects
     * @throws IllegalArgumentException if there are not enough quests available
     */
    List<Quest> generateRandomsQuests();

    /**
     * Calculates the total points for a player's answers to quests.
     *
     * @param answersDto the {@link AnswersDto} containing the player's answers
     * @return the total points calculated from the answers
     * @throws IllegalArgumentException if the provided {@code answersDto} is {@code null}
     */
    int calculatePoints(AnswersDto answersDto);

    /**
     * Removes a quest associated with a specific player ID.
     *
     * @param playerID the unique ID of the player whose associated quest is to be removed
     * @throws IllegalArgumentException if the quest for the specified player ID is not found
     */
    void removePlayer(Long playerID);

    /**
     * Finds a quest by its unique ID.
     *
     * @param questID the unique ID of the quest
     * @return the {@link Quest} object if found
     * @throws IllegalArgumentException if the quest is not found
     */
    Quest findQuest(Long questID);
}