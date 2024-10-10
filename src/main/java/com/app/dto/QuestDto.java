package com.app.dto;

import com.app.model.difficulty_level.DifficultyLevel;

import java.util.Map;

/**
 * A Data Transfer Object (DTO) representing a quest with its details, including the question, difficulty level, and possible answers.
 *
 * @param id             The unique identifier of the quest.
 * @param difficultyLevel The difficulty level of the quest, represented by a {@link DifficultyLevel} object.
 * @param category       The category or topic of the quest.
 * @param question       The text of the quest question.
 * @param answers        A map where the keys are answer identifiers (String) and the values are the corresponding answer texts (String).
 */
public record QuestDto(Long id, DifficultyLevel difficultyLevel, String category, String question,
                       Map<String, String> answers) {
}