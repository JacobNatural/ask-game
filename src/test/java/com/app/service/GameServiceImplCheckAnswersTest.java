package com.app.service;

import com.app.difficulty_level.DifficultyLevel;
import com.app.game.service.PlayerService;
import com.app.game.service.QuestService;
import com.app.game.service.impl.GameServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.app.data_provider.DataProvider.ANSWERS_DTO_1;
import static com.app.data_provider.DataProvider.PLAYER1;

@ExtendWith(MockitoExtension.class)
public class GameServiceImplCheckAnswersTest {

    @Mock
    private QuestService<DifficultyLevel> questService;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private GameServiceImpl gameServiceImpl;

    @Test
    @DisplayName("When answerDto is null")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                gameServiceImpl.checkAnswers(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AnswersDto cannot be null");
    }

    @Test
    @DisplayName("When the player not exist")
    public void test2(){

        Mockito.when(playerService.containID(ArgumentMatchers.anyLong()))
                        .thenReturn(false);

        Assertions.assertThatThrownBy(() ->
                        gameServiceImpl.checkAnswers(ANSWERS_DTO_1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Player not found");
    }

    @Test
    @DisplayName("When check answer and update statistic for game")
    public void test3(){

        Mockito.when(playerService.containID(ArgumentMatchers.anyLong()))
                .thenReturn(true);

        Mockito.when(questService.calculatePoints(ArgumentMatchers.any()))
                        .thenReturn(130);

        Mockito.when(playerService.findPlayer(1L))
                        .thenReturn(PLAYER1);

        Assertions.assertThat(
                        gameServiceImpl.checkAnswers(ANSWERS_DTO_1))
                .isEqualTo(PLAYER1);

        System.out.println(PLAYER1);

        Mockito.verify(playerService, Mockito.times(1))
                .containID(ArgumentMatchers.anyLong());

        Mockito.verify(questService, Mockito.times(1))
                .calculatePoints(ArgumentMatchers.any());
    }
}
