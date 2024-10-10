package com.app.service.impl;

import com.app.dto.AnswersDto;
import com.app.repository.StatisticRepository;
import com.app.repository.model.Statistic;
import com.app.service.QuestService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.app.service.ServiceTestData.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {

    @Mock
    private QuestService questService;

    @Mock
    private StatisticRepository statisticRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    @DisplayName("When generating a random quest and mapping it to QuestDto")
    public void test1(){

        Mockito.when(questService.generateRandomsQuests())
                .thenReturn(List.of(
                        QUEST_DATA_1,QUEST_DATA_2));

        Assertions.assertThat(gameService.generateQuests())
                .contains(QUEST_DTO_1, QUEST_DTO_2);

        Mockito.verify(questService, Mockito.times(1))
                .generateRandomsQuests();
    }

    @Test
    @DisplayName("When calculating the answer and answerDto is null")
    public void test2(){

        Assertions.assertThatThrownBy(() ->
                gameService.checkAnswers(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AnswersDto cannot be null");
    }
    
    @Test
    @DisplayName("When calculating the answer and generating statistics")
    public void test3(){

        Mockito
                .when(questService.calculatePoints(ArgumentMatchers.any(AnswersDto.class)))
                .thenReturn(70);

        Mockito
                .when(statisticRepository.save(ArgumentMatchers.any(Statistic.class)))
                .thenReturn(
                        new Statistic(1L, 1L, LocalDateTime.parse("2024-01-01T22:22"),70));

        Assertions.assertThat(gameService.checkAnswers(ANSWERS_DTO_1))
                .isEqualTo(Map.entry(LocalDateTime.parse("2024-01-01T22:22"), 70));

        var inOrder = Mockito.inOrder(questService,statisticRepository);

        inOrder.verify(questService, Mockito.times(1))
                .calculatePoints(ArgumentMatchers.any(AnswersDto.class));

        inOrder.verify(statisticRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Statistic.class));
    }



}
