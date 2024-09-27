package com.app.quest;

import com.app.difficulty_level.DifficultyLevel;
import com.app.dto.QuestDto;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a quest with a unique ID, difficulty level, category, question, and possible answers.
 *
 * <p>This class provides methods to create a QuestDto representation, check if an answer is correct,
 * calculate points based on the difficulty level, and format the quest for text output.</p>
 */
@EqualsAndHashCode(of = {"question"})
@ToString
public class Quest {
    final Long id;
    final DifficultyLevel difficultyLevel;
    final String category;
    final String question;
    public final Map<String, Boolean> answers;

    static Long idCounter = 1L;

    /**
     * Constructs a new Quest with a generated ID.
     *
     * @param difficultyLevel the difficulty level of the quest
     * @param category        the category of the quest
     * @param question        the question text
     * @param answers         a map of possible answers and their correctness
     */
    public Quest(DifficultyLevel difficultyLevel, String category, String question, Map<String, Boolean> answers) {
        this.id = idCounter++;
        this.difficultyLevel = difficultyLevel;
        this.category = category;
        this.question = question;
        this.answers = answers;
    }

    static final Map<DifficultyLevel, Integer> scores = Map.of(
            DifficultyLevel.A, 10,
            DifficultyLevel.B, 15,
            DifficultyLevel.C, 20);

    /**
     * Creates a QuestDto representation of the quest.
     *
     * @return a QuestDto containing the quest's details
     */
    public QuestDto createQuestDto() {
        var map = new HashMap<String, String>();
        var index = 'a';

        for (var answer : answers.keySet()) {
            map.put(String.valueOf(index), answer);
            index++;
        }
        return new QuestDto(id, difficultyLevel, category, question, map);
    }

    /**
     * Checks if the quest contains a specified answer.
     *
     * @param answer the answer to check
     * @return true if the answer is present, false otherwise
     */
    public boolean containAnswer(String answer) {
        return answers.containsKey(answer);
    }

    /**
     * Calculates the points awarded for a given answer.
     *
     * @param answer the answer to evaluate
     * @return the points awarded based on the correctness of the answer and the quest's difficulty level
     */
    public int calculatePoints(String answer) {
        if (answers.get(answer)) {
            return scores.get(difficultyLevel);
        }
        return 0;
    }

    /**
     * Converts a Quest object to a formatted string representation for text output.
     *
     * @param quest the quest to format
     * @return a string representation of the quest's details
     */
    public static String toTxtFormat(Quest quest) {
        var sb = new StringBuilder();

        var answerString = quest.answers
                .entrySet()
                .stream()
                .map(map -> String.format("%s-%s", map.getKey(), map.getValue()))
                .collect(Collectors.joining(";"));

        sb.append(quest.difficultyLevel)
                .append(";")
                .append(quest.category)
                .append(";")
                .append(quest.question)
                .append(";")
                .append(answerString)
                .append(";");

        return sb.toString();
    }

    /**
     * Checks if the quest has a specified difficulty level.
     *
     * @param difficultyLevel the difficulty level to check
     * @return true if the quest's difficulty level matches, false otherwise
     */
    public boolean hasDifficultLevel(DifficultyLevel difficultyLevel) {
        return this.difficultyLevel.equals(difficultyLevel);
    }
}