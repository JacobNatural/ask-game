package com.app.service;

import com.app.dto.AnswersDto;
import com.app.dto.QuestDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Interface representing the game service, providing functionalities
 * for generating quests and checking answers.
 */
public interface GameService {

    /**
     * Generates a list of quests for the game.
     *
     * @return a list of QuestDto objects representing the generated quests.
     */
    List<QuestDto> generateQuests();

    /**
     * Checks the player's answers against the correct answers and calculates the score.
     *
     * @param answersDto the AnswersDto object containing the player's answers.
     * @return a Map.Entry where the key is a LocalDateTime representing the time of the attempt,
     *         and the value is an Integer representing the score achieved.
     * @throws IllegalArgumentException if the provided answersDto is null.
     */
    Map.Entry<LocalDateTime, Integer> checkAnswers(AnswersDto answersDto);
}
