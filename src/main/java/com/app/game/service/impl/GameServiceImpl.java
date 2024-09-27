package com.app.game.service.impl;

import com.app.difficulty_level.DifficultyLevel;
import com.app.dto.AnswersDto;
import com.app.dto.QuestDto;
import com.app.game.service.GameService;
import com.app.game.service.PlayerService;
import com.app.game.service.QuestService;
import com.app.player.Player;
import com.app.quest.Quest;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Service implementation for managing game-related functionality.
 * Handles quest generation and player answer checking.
 */
@Service
@ToString
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final PlayerService playerService;
    private final QuestService<DifficultyLevel> questService;

    /**
     * Generates a list of random quests based on different difficulty levels.
     *
     * @return a list of {@link QuestDto} objects representing the generated quests.
     */
    @Override
    public List<QuestDto> generateQuests() {
        return questService
                .generateRandomsQuests()
                .stream()
                .map(Quest::createQuestDto)
                .toList();
    }

    /**
     * Checks the player's answers, calculates their score, and updates their statistics.
     *
     * @param answersDto the {@link AnswersDto} object containing the player's answers and player ID.
     * @return the {@link Player} object with updated statistics.
     * @throws IllegalArgumentException if the {@code answersDto} is null or the player is not found.
     */
    @Override
    public Player checkAnswers(AnswersDto answersDto) {

        if (answersDto == null) {
            throw new IllegalArgumentException("AnswersDto cannot be null");
        }

        if (!playerService.containID(answersDto.playerId())) {
            throw new IllegalArgumentException("Player not found");
        }

        var player = playerService.findPlayer(answersDto.playerId());
        player.updateStatistic(Map.of(
                LocalDateTime.now(),
                questService.calculatePoints(answersDto)));

        return player;
    }

}