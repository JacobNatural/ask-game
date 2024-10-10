package com.app.dto;

import java.util.Map;

/**
 * A Data Transfer Object (DTO) representing a collection of answers submitted by a player.
 *
 * @param playerId The ID of the player submitting the answers.
 * @param answers A map where the keys are question IDs (Long) and the values are the corresponding answers (String).
 */
public record AnswersDto(Long playerId, Map<Long, String> answers) {
}