package com.app.service;

import com.app.game.service.impl.PlayerServiceImpl;
import com.app.player.Player;
import com.app.repository.PlayerRepository;
import com.app.txt.save.FileWriter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplGetTheBestPlayerInPeriodTest {
    @Mock
    private PlayerRepository<Player> playerRepository;

    @Mock
    private FileWriter<Player> fileWriter;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    @DisplayName("When the 'from' date is null")
    public void test1(){

        Assertions.assertThatThrownBy(() ->
                playerService.getTheBestPlayerInPeriod(null, LocalDateTime.parse("2024-02-02T22:22")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Start date cannot be null");
    }

    @Test
    @DisplayName("When the 'to' date is null")
    public void test2(){

        Assertions.assertThatThrownBy(() ->
                        playerService.getTheBestPlayerInPeriod(LocalDateTime.parse("2024-02-02T22:22"),null ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("End date cannot be null");
    }

    @Test
    @DisplayName("When the from date is after to date null")
    public void test3(){

        Assertions.assertThatThrownBy(() ->
                        playerService.getTheBestPlayerInPeriod(
                                LocalDateTime.parse("2024-02-02T22:22"),LocalDateTime.parse("2022-02-02T22:22") ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Start date cannot be after end date");
    }

    @Test
    @DisplayName("When we got the best player during the period")
    public void test4(){

        Mockito.when(playerRepository.getPlayers())
                        .thenReturn(List.of(
                                PLAYER1, PLAYER2,
                                PLAYER3, PLAYER4,
                                PLAYER5, PLAYER6));

        Assertions.assertThat(playerService.getTheBestPlayerInPeriod(
                                LocalDateTime.parse("2024-03-02T22:22"),LocalDateTime.parse("2024-06-02T22:22")))
                .isEqualTo(List.of(PLAYER2));

        Mockito.verify(playerRepository, Mockito.times(1))
                .getPlayers();
    }

}
