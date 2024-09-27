package com.app.game.service;

import com.app.dto.AnswersDto;
import com.app.dto.QuestDto;
import com.app.player.Player;

import java.util.List;

/**
 * Service interface for game-related operations.
 *
 * <p>This interface defines methods for generating quests and checking player answers.</p>
 */
public interface GameService {

    /**
     * Generates a list of quests for the game.
     *
     * @return a list of {@link QuestDto} representing the generated quests
     */
    List<QuestDto> generateQuests();

    /**
     * Checks the player's answers against the correct answers for the quests.
     *
     * @param answersDto the {@link AnswersDto} containing the player's answers
     * @return the {@link Player} object after updating their statistics based on the answers
     * @throws IllegalArgumentException if the provided {@code answersDto} is {@code null}
     *                                  or if the player ID in {@code answersDto} does not exist
     */
    Player checkAnswers(AnswersDto answersDto);
}