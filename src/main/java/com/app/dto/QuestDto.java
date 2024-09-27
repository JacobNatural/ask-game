package com.app.dto;

import com.app.difficulty_level.DifficultyLevel;

import java.util.Map;

/**
 * Data Transfer Object (DTO) representing a quest or question in the game.
 *
 * <p>This record holds the following data:
 * <ul>
 *     <li>{@link #id()} - The unique ID of the quest.</li>
 *     <li>{@link #difficultyLevel()} - The {@link DifficultyLevel} of the quest, indicating how challenging it is.</li>
 *     <li>{@link #category()} - The category or topic of the quest.</li>
 *     <li>{@link #question()} - The actual question text for the quest.</li>
 *     <li>{@link #answers()} - A map of possible answers, where the key is the answer option (e.g., "A", "B")
 *     and the value is the corresponding answer text.</li>
 * </ul>
 *
 * @param id              the unique ID of the quest
 * @param difficultyLevel the difficulty level of the quest
 * @param category        the category or topic of the quest
 * @param question        the text of the question being asked
 * @param answers         a map of answer options (e.g., "A", "B") and their corresponding text
 */
public record QuestDto(Long id, DifficultyLevel difficultyLevel, String category, String question,
                       Map<String, String> answers) {
}