package com.app.txt.model;

import com.app.difficulty_level.DifficultyLevel;

import java.util.Map;

/**
 * Represents the data for a quest, including its difficulty level, category, question, and possible answers.
 *
 * @param difficultyLevel the difficulty level of the quest
 * @param category        the category under which the quest falls
 * @param question        the text of the question being asked in the quest
 * @param answers         a map of possible answers where the key is the answer text and the value indicates whether it is correct
 */
public record QuestData(DifficultyLevel difficultyLevel, String category, String question,
                        Map<String, Boolean> answers) {
}