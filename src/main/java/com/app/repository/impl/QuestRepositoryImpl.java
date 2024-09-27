package com.app.repository.impl;

import com.app.quest.Quest;
import com.app.quest.QuestMapper;
import com.app.repository.QuestRepository;
import com.app.txt.load.FileReader;
import com.app.txt.model.QuestData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link QuestRepository} interface using a concurrent map for thread-safe quest storage.
 *
 * <p>This class is responsible for loading quest data from a specified file and providing methods to manage
 * quest records, including adding, removing, and retrieving quests.</p>
 */
@Component
public class QuestRepositoryImpl implements QuestRepository<Long> {

    private final ConcurrentMap<Long, Quest> quests;

    /**
     * Constructs a new QuestRepositoryImpl.
     *
     * @param filename the name of the file from which to load quest data
     * @param fileReader     the loading strategy for quest data
     */
    public QuestRepositoryImpl(@Value("${questsFilename}") String filename, FileReader<QuestData> fileReader) {
        this.quests = fileReader
                .load(filename)
                .stream()
                .map(questData -> new Quest(
                        questData.difficultyLevel(), questData.category(), questData.question(), questData.answers()))
                .collect(Collectors.toConcurrentMap(QuestMapper.toId::applyAsLong, Function.identity()));
    }

    /**
     * Retrieves all quests in the repository.
     *
     * @return a map of all quests, keyed by their IDs
     */
    @Override
    public Map<Long, Quest> getAll() {
        return quests;
    }

    /**
     * Gets a list of all quests.
     *
     * @return a list containing all quests
     */
    @Override
    public List<Quest> getQuests() {
        return quests.values().stream().toList();
    }

    /**
     * Checks if a quest with the specified ID exists in the repository.
     *
     * @param id the ID of the quest to check
     * @return true if the quest exists; false otherwise
     */
    @Override
    public boolean containsID(Long id) {
        return quests.containsKey(id);
    }

    /**
     * Finds a quest by its ID.
     *
     * @param id the ID of the quest to find
     * @return the quest associated with the given ID, or null if not found
     */
    @Override
    public Quest findByID(Long id) {
        return quests.get(id);
    }

    /**
     * Checks if the specified quest exists in the repository.
     *
     * @param quest the quest to check
     * @return true if the quest exists; false otherwise
     */
    public boolean containsValue(Quest quest) {
        return quests.containsValue(quest);
    }

    /**
     * Adds a new quest to the repository.
     *
     * @param quest the quest to add
     */
    @Override
    public void addQuest(Quest quest) {
        quests.put(QuestMapper.toId.applyAsLong(quest), quest);
    }

    /**
     * Removes a quest from the repository by its ID.
     *
     * @param id the ID of the quest to remove
     */
    @Override
    public void removeByID(Long id) {
        quests.remove(id);
    }
}