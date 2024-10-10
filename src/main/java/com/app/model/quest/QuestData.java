package com.app.model.quest;

import com.app.model.difficulty_level.DifficultyLevel;
import com.app.dto.QuestDto;
import com.app.repository.model.Answer;
import com.app.repository.model.Quest;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents the data of a quest, including its question, answers, and difficulty level.
 * <p>
 * This class encapsulates details about a quest, including the question text, difficulty level,
 * category, and possible answers with their correctness status.
 * </p>
 */
@EqualsAndHashCode(of = {"question"})
@ToString
public class QuestData {
    final Long id;
    final DifficultyLevel difficultyLevel;
    final String category;
    final String question;
    public final Map<String, Boolean> answers;

    /**
     * Constructs a new QuestData instance.
     *
     * @param id             The unique identifier of the quest.
     * @param difficultyLevel The difficulty level of the quest.
     * @param category       The category of the quest.
     * @param question       The question text of the quest.
     * @param answers        A map of possible answers and their correctness status.
     */
    public QuestData(Long id, DifficultyLevel difficultyLevel, String category, String question, Map<String, Boolean> answers) {
        this.id = id;
        this.difficultyLevel = difficultyLevel;
        this.category = category;
        this.question = question;
        this.answers = answers;
    }

    /**
     * Constructs a new QuestData instance from a Quest object and its associated answers.
     *
     * @param quest  The Quest object containing basic quest information.
     * @param answers A map of possible answers and their correctness status.
     */
    public QuestData(Quest quest, Map<String, Boolean> answers) {
        this.id = quest.getId();
        this.difficultyLevel = quest.getDifficultyLevel();
        this.category = quest.getCategory();
        this.question = quest.getQuestion();
        this.answers = answers;
    }

    // A map to define point values for each difficulty level.
    static final Map<DifficultyLevel, Integer> scores = Map.of(
            DifficultyLevel.A, 10,
            DifficultyLevel.B, 15,
            DifficultyLevel.C, 20);

    /**
     * Creates a QuestData instance from a Quest object and a list of answers.
     *
     * @param quest  The Quest object containing basic quest information.
     * @param answers A list of Answer objects representing the possible answers.
     * @return A new QuestData instance initialized with the quest and answer details.
     */
    public static QuestData fromQuestAndAnswers(Quest quest, List<Answer> answers) {
        return new QuestData(
                quest.getId(),
                quest.getDifficultyLevel(),
                quest.getCategory(),
                quest.getQuestion(),
                toAnswersMap(answers));
    }

    /**
     * Creates a QuestData instance from a Quest object, initializing with an empty answers map.
     *
     * @param quest The Quest object containing basic quest information.
     * @return A new QuestData instance initialized with the quest details and an empty answers map.
     */
    public static QuestData fromQuest(Quest quest) {
        return new QuestData(
                quest.getId(),
                quest.getDifficultyLevel(),
                quest.getCategory(),
                quest.getQuestion(),
                new HashMap<>());
    }

    /**
     * Creates a QuestDto instance representing the quest data.
     *
     * @return A QuestDto object containing the quest's ID, difficulty level, category,
     *         question text, and possible answers.
     */
    public QuestDto createQuestDto() {
        var map = new HashMap<String, String>();
        var index = 'a';

        for (var answer : answers.keySet().stream().sorted(Comparator.naturalOrder()).toList()) {
            map.put(String.valueOf(index), answer);
            index++;
        }
        return new QuestDto(id, difficultyLevel, category, question, map);
    }

    /**
     * Calculates the points awarded for a given answer based on its correctness and the quest's difficulty level.
     *
     * @param answer The answer provided by the user.
     * @return The points awarded, or 0 if the answer is incorrect.
     */
    public int calculatePoints(String answer) {
        var isCorrect = answers.get(answer);
        if (isCorrect != null && isCorrect) {
            return scores.get(difficultyLevel);
        }
        return 0;
    }

    /**
     * Converts this QuestData instance back into a Quest object.
     *
     * @return A new Quest object initialized with this QuestData's properties.
     */
    public Quest toQuest() {
        return new Quest(id, difficultyLevel, category, question);
    }

    /**
     * Converts this QuestData's answers to a list of Answer objects.
     *
     * @return A list of Answer objects representing the quest's answers.
     */
    public List<Answer> toAnswer() {
        return answers
                .entrySet()
                .stream()
                .map(map -> new Answer(null, id, map.getKey(), map.getValue()))
                .toList();
    }

    /**
     * Converts a list of Answer objects into a map of answer text to its correctness status.
     *
     * @param answers A list of Answer objects to convert.
     * @return A map of answers and their correctness status.
     */
    private static Map<String, Boolean> toAnswersMap(List<Answer> answers) {
        return answers
                .stream()
                .collect(Collectors.toMap(Answer::getAnswer, Answer::isConfirm));
    }
}