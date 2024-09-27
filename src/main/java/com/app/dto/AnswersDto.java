package com.app.dto;

import java.util.Map;

/**
 * Data Transfer Object (DTO) representing the answers submitted by a player.
 *
 * <p>This record holds the following data:
 * <ul>
 *     <li>{@link #playerId()} - The unique ID of the player submitting the answers.</li>
 *     <li>{@link #answers()} - A map containing the player's answers, where the key is the question ID
 *     and the value is the player's submitted answer as a {@link String}.</li>
 * </ul>
 *
 * @param playerId the ID of the player
 * @param answers  a map of question IDs and the corresponding player's answers
 */
public record AnswersDto(Long playerId, Map<Long, String> answers) {
}