package com.app.service.impl;

import com.app.converter.FromQuestWithAnswerToQuestData;
import com.app.model.difficulty_level.DifficultyLevel;
import com.app.dto.AnswersDto;
import com.app.repository.model.Quest;
import com.app.service.QuestCacheService;
import com.app.service.QuestService;
import com.app.model.quest.QuestData;
import com.app.repository.AnswerRepository;
import com.app.repository.QuestRepository;
import com.app.repository.QuestWithAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation of the QuestService interface, providing functionalities for managing quests.
 */
@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {
    private final QuestRepository questRepository;
    private final AnswerRepository answerRepository;
    private final QuestWithAnswerRepository questWithAnswerRepository;
    private final QuestCacheService<DifficultyLevel> questCacheService;
    private final FromQuestWithAnswerToQuestData fromQuestWithAnswerToQuestData;
    private static final int AMOUNT_OF_ASK = 4;
    /**
     * Adds a new quest along with its associated answers to the repository.
     *
     * @param quest the QuestData object containing quest and answer information.
     * @return the saved QuestData object after persisting it to the database.
     * @throws IllegalArgumentException if the provided quest is null.
     */
    @Override
    public QuestData addQuest(QuestData quest) {
        if (quest == null) {
            throw new IllegalArgumentException("Quest cannot be null");
        }

        return QuestData.fromQuestAndAnswers(
                questRepository.save(quest.toQuest()),
                answerRepository.saveAll(quest.toAnswer()));
    }

    /**
     * Removes a quest identified by its ID from the repository.
     *
     * @param questId the ID of the quest to be removed.
     * @return the QuestData object representing the removed quest.
     */
    @Override
    public QuestData removeQuest(Long questId) {
        return QuestData.fromQuest(questRepository.delete(questId));
    }

    /**
     * Retrieves a quest along with its associated answers using its ID.
     *
     * @param questID the ID of the quest to be retrieved.
     * @return the QuestData object containing the quest and its answers.
     */
    @Override
    public QuestData findQuest(Long questID) {
        return fromQuestWithAnswerToQuestData
                .toList(questWithAnswerRepository.findQuestWithAnswer(questID))
                .getFirst();
    }

    /**
     * Generates a list of random quests based on the configured number of questions to ask.
     *
     * @return a list of randomly selected QuestData objects.
     */
    @Override
    public List<QuestData> generateRandomsQuests() {
        return questCacheService
                .getAll()
                .values()
                .stream()
                .map(questList -> getRandomLimited(AMOUNT_OF_ASK, new ArrayList<>(questList)))
                .flatMap(Collection::stream)
                .toList();
    }

    /**
     * Calculates the total points based on the provided answers.
     *
     * @param answersDto the AnswersDto object containing the player’s answers.
     * @return the total points calculated based on correct answers.
     * @throws IllegalArgumentException if the provided answersDto is null.
     */
    @Override
    public int calculatePoints(AnswersDto answersDto) {
        if (answersDto == null) {
            throw new IllegalArgumentException("AnswersDto cannot be null");
        }

        var questDataMap = getQuestData(new ArrayList<>(answersDto.answers().keySet()));

        return answersDto
                .answers()
                .entrySet()
                .stream()
                .map(answerDto -> {
                    if (!questDataMap.containsKey(answerDto.getKey())) {
                        return 0;
                    }
                    return questDataMap.get(answerDto.getKey())
                            .calculatePoints(answerDto.getValue());
                })
                .reduce(0, Integer::sum);  // Default to 0 for empty cases
    }

    /**
     * Retrieves quest data for the specified list of quest IDs.
     *
     * @param questIds a list of quest IDs for which to retrieve data.
     * @return a map of quest IDs to their corresponding QuestData objects.
     */
    private Map<Long, QuestData> getQuestData(List<Long> questIds) {
        return fromQuestWithAnswerToQuestData
                .toMap(questWithAnswerRepository.findQuestsWithAnswersByIds(questIds), Quest::getId);
    }

    /**
     * Selects a limited number of random quests from a provided list.
     *
     * @param n the number of quests to select.
     * @param quests the list of quests to choose from.
     * @return a list containing randomly selected QuestData objects.
     * @throws IllegalArgumentException if there are not enough quests to select from.
     */
    private List<QuestData> getRandomLimited(int n, List<QuestData> quests) {
        if (quests.size() < n) {
            throw new IllegalArgumentException("Not enough quests");
        }
        var randomAnswers = new ArrayList<QuestData>();

        for (int i = 0; i < n; i++) {
            int randIndex = randNumber(0, quests.size());
            var randQuestion = quests.get(randIndex);
            randomAnswers.add(randQuestion);
            quests.remove(randIndex);  // Remove by index to maintain list integrity
        }
        return randomAnswers;
    }

    private static int randNumber(int min, int max){
        return new Random().nextInt(min, max);
    }
}
