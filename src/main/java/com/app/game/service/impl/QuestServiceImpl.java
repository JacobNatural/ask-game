package com.app.game.service.impl;

import com.app.difficulty_level.DifficultyLevel;
import com.app.dto.AnswersDto;
import com.app.game.service.QuestCacheService;
import com.app.game.service.QuestService;
import com.app.quest.Quest;
import com.app.repository.QuestRepository;
import com.app.txt.save.FileWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * Service implementation for managing quests in the game.
 *
 * <p>This class provides methods to add, remove, find, save quests, generate random quests,
 * and calculate points based on player answers.
 */
@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService<DifficultyLevel> {
    private final QuestRepository<Long> questRepository;
    private final QuestCacheService<DifficultyLevel> questCacheService;
    private final FileWriter<Quest> questFileWriter;

    @Value("${amountOfAsk}")
    private final int AMOUNT_OF_ASK = 4;

    /**
     * Adds a new quest to the repository.
     *
     * @param quest the {@link Quest} object to be added
     * @throws IllegalArgumentException if the quest is {@code null} or already exists in the repository
     */
    @Override
    public void addQuest(Quest quest) {
        if (quest == null) {
            throw new IllegalArgumentException("Quest cannot be null");
        }

        if (questRepository.containsValue(quest)) {
            throw new IllegalArgumentException("Quest already exists");
        }
        questRepository.addQuest(quest);
    }

    /**
     * Removes a quest from the repository by ID.
     *
     * @param playerID the unique ID of the quest to be removed
     * @throws IllegalArgumentException if the quest with the specified ID is not found
     */
    @Override
    public void removePlayer(Long playerID) {
        if (!questRepository.containsID(playerID)) {
            throw new IllegalArgumentException("Quest not found");
        }

        questRepository.removeByID(playerID);
    }

    /**
     * Finds a quest by its unique ID.
     *
     * @param questID the unique ID of the quest
     * @return the {@link Quest} object if found
     * @throws IllegalArgumentException if the quest is not found
     */
    @Override
    public Quest findQuest(Long questID) {
        if (!questRepository.containsID(questID)) {
            throw new IllegalArgumentException("Quest not found");
        }

        return questRepository.findByID(questID);
    }

    /**
     * Saves the current list of quests to a text file using a specified format.
     *
     * @param filename    the name of the file where quests will be saved
     * @param toTxtFormat a function that converts a {@link Quest} object to a text format
     * @throws IllegalArgumentException if the {@code toTxtFormat} function is {@code null}
     */
    @Override
    public void saveQuestToTxt(String filename, Function<Quest, String> toTxtFormat) {
        if (toTxtFormat == null) {
            throw new IllegalArgumentException("ToTxtFormat cannot be null");
        }

        questFileWriter.save(filename, questRepository.getQuests(), toTxtFormat);
    }

    /**
     * Generates a list of random quests limited to the predefined amount.
     *
     * @return a list of randomly selected {@link Quest} objects
     * @throws IllegalArgumentException if there are not enough quests available
     */
    @Override
    public List<Quest> generateRandomsQuests() {
        return questCacheService
                .getAll()
                .values()
                .stream()
                .map(questList -> getRandomLimited(AMOUNT_OF_ASK, questList))
                .flatMap(Collection::stream)
                .toList();
    }

    /**
     * Calculates the total points for a player's answers.
     *
     * @param answersDto the {@link AnswersDto} containing player answers
     * @return the total points calculated from the answers
     * @throws IllegalArgumentException if the {@code answersDto} is {@code null}
     */
    @Override
    public int calculatePoints(AnswersDto answersDto) {
        if (answersDto == null) {
            throw new IllegalArgumentException("AnswersDto cannot be null");
        }
        return answersDto
                .answers()
                .entrySet()
                .stream()
                .map(answerDto -> calculatePoint(answerDto.getKey(), answerDto.getValue()))
                .reduce(Integer::sum)
                .orElseThrow();
    }

    /**
     * Calculates the points for a specific answer to a quest.
     *
     * @param questId the unique ID of the quest
     * @param answer  the player's answer
     * @return the points awarded for the given answer
     * @throws IllegalArgumentException if the quest is not found or the answer is not valid
     */
    private int calculatePoint(Long questId, String answer) {
        if (!questRepository.containsID(questId)) {
            throw new IllegalArgumentException("Quest not found");
        }

        var quest = questRepository.findByID(questId);

        if (!quest.containAnswer(answer)) {
            throw new IllegalArgumentException("Answer not found");
        }

        return quest.calculatePoints(answer);
    }

    /**
     * Retrieves a limited number of random quests from the provided list.
     *
     * @param n      the number of quests to retrieve
     * @param quests the list of quests to select from
     * @return a list of randomly selected {@link Quest} objects
     * @throws IllegalArgumentException if there are not enough quests in the list
     */
    private List<Quest> getRandomLimited(int n, List<Quest> quests) {
        if (quests.size() < n) {
            throw new IllegalArgumentException("Not enough quests");
        }
        var randomAnswers = new ArrayList<Quest>();
        for (int i = 0; i < n; i++) {
            var randQuestion =
                    quests.get(new Random().nextInt(0, quests.size()));
            randomAnswers.add(randQuestion);
            quests.remove(randQuestion);
        }
        return randomAnswers;
    }
}