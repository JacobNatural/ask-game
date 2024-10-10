package com.app.service.impl;

import com.app.dto.AnswersDto;
import com.app.dto.QuestDto;
import com.app.service.GameService;
import com.app.service.QuestService;
import com.app.model.quest.QuestData;
import com.app.repository.StatisticRepository;
import com.app.repository.model.Statistic;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the GameService interface, providing methods for generating quests
 * and checking answers. This service interacts with the QuestService and the StatisticRepository
 * to perform its operations.
 */
@Service
@ToString
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final QuestService questService;
    private final StatisticRepository statisticRepository;

    /**
     * Generates a list of random quests and converts them to QuestDto objects.
     *
     * @return a list of QuestDto representing the generated quests
     */
    @Override
    public List<QuestDto> generateQuests() {
        return questService
                .generateRandomsQuests()
                .stream()
                .map(QuestData::createQuestDto)
                .toList();
    }

    /**
     * Checks the provided answers against the correct ones, calculates the score,
     * and saves the statistics for the player.
     *
     * @param answersDto the DTO containing the player's answers
     * @return a Map.Entry containing the time the statistic was recorded and the score
     * @throws IllegalArgumentException if the answersDto is null
     */
    @Override
    public Map.Entry<LocalDateTime, Integer> checkAnswers(AnswersDto answersDto) {
        if (answersDto == null) {
            throw new IllegalArgumentException("AnswersDto cannot be null");
        }

        // Calculate points based on the provided answers
        var points = questService.calculatePoints(answersDto);

        // Save the statistic for the player's score
        var statistic = statisticRepository.save(
                new Statistic(null, answersDto.playerId(), LocalDateTime.now(), points)
        );

        // Return the time the statistic was recorded and the score
        return Map.entry(statistic.getStat_time(), statistic.getScore());
    }
}
