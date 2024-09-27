package com.app.repository;

import com.app.quest.Quest;

import java.util.List;
import java.util.Map;

/**
 * Interface for managing quest data in a repository.
 *
 * <p>This interface defines the operations available for interacting with quest records,
 * including adding, removing, and retrieving quests.</p>
 *
 * @param <T> the type of the identifier used for quests
 */
public interface QuestRepository<T> {

    /**
     * Retrieves all quests in the repository.
     *
     * @return a map of all quests, keyed by their identifiers
     */
    Map<T, Quest> getAll();

    /**
     * Gets a list of all quests.
     *
     * @return a list containing all quests
     */
    List<Quest> getQuests();

    /**
     * Adds a new quest to the repository.
     *
     * @param quest the quest to add
     */
    void addQuest(Quest quest);

    /**
     * Finds a quest by its ID.
     *
     * @param id the ID of the quest to find
     * @return the quest associated with the given ID, or null if not found
     */
    Quest findByID(Long id);

    /**
     * Removes a quest from the repository by its ID.
     *
     * @param id the ID of the quest to remove
     */
    void removeByID(Long id);

    /**
     * Checks if a quest with the specified ID exists in the repository.
     *
     * @param id the ID of the quest to check
     * @return true if the quest exists; false otherwise
     */
    boolean containsID(Long id);

    /**
     * Checks if the specified quest exists in the repository.
     *
     * @param quest the quest to check
     * @return true if the quest exists; false otherwise
     */
    boolean containsValue(Quest quest);
}