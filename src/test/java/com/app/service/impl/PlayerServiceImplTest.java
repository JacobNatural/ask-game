package com.app.service.impl;

import com.app.converter.FromPlayerWithStatisticToPlayerData;
import com.app.repository.PlayerRepository;
import com.app.repository.PlayersWithStatisticsRepository;
import com.app.repository.model.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.app.service.ServiceTestData.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings
public class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayersWithStatisticsRepository playersWithStatisticsRepository;

    @Mock
    private FromPlayerWithStatisticToPlayerData fromPlayerWithStatisticToPlayerData;

    @InjectMocks
    PlayerServiceImpl playerService;

    @Test
    @DisplayName("When saving the Player and PlayerDto is null")
    public void test1() {

        Assertions.assertThatThrownBy(() ->
                        playerService.addPlayer(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("PlayerDto cannot be null");
    }

    @Test
    @DisplayName("When saving the Player")
    public void test2() {

        Mockito.when(playerRepository
                .save(ArgumentMatchers.any(Player.class))).thenReturn(PLAYER_1);

        Assertions.assertThat(playerService.addPlayer(PLAYER_DTO_1))
                .isEqualTo(PLAYER_DATA_1);

        Mockito.verify(playerRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Player.class));
    }

    @Test
    @DisplayName("When removing player")
    public void test3() {

        Mockito.when(playerRepository.delete(ArgumentMatchers.anyLong()))
                .thenReturn(PLAYER_1);

        Assertions.assertThat(playerService.removePlayer(1L))
                .isEqualTo(PLAYER_DATA_1);

        Mockito.verify(playerRepository, Mockito.times(1))
                .delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("When finding a player by id")
    public void test4() {

        Mockito.when(playersWithStatisticsRepository.findPlayerWithStatistic(ArgumentMatchers.anyLong()))
                .thenReturn(List.of(PLAYER_WITH_STATISTIC_1));

        Mockito.when(fromPlayerWithStatisticToPlayerData.toList(ArgumentMatchers.anyList()))
                .thenReturn(List.of(PLAYER_DATA_2));

        Assertions.assertThat(playerService.findPlayer(1L))
                .isEqualTo(PLAYER_DATA_2);

        var inOrder = Mockito
                .inOrder(playersWithStatisticsRepository, fromPlayerWithStatisticToPlayerData);

        inOrder
                .verify(playersWithStatisticsRepository, Mockito.times(1))
                .findPlayerWithStatistic(ArgumentMatchers.anyLong());

        inOrder
                .verify(fromPlayerWithStatisticToPlayerData, Mockito.times(1))
                .toList(ArgumentMatchers.anyList());
    }

    @Test
    @DisplayName("When finding the best player")
    public void test5() {

        Mockito.when(playersWithStatisticsRepository.getTheBestPlayer())
                .thenReturn(List.of(PLAYER_WITH_STATISTIC_1));

        Mockito.when(fromPlayerWithStatisticToPlayerData.toList(ArgumentMatchers.anyList()))
                .thenReturn(List.of(PLAYER_DATA_2));

        Assertions.assertThat(playerService.getTheBestPlayer())
                .isEqualTo(List.of(PLAYER_DATA_2));

        var inOrder = Mockito
                .inOrder(playersWithStatisticsRepository, fromPlayerWithStatisticToPlayerData);

        inOrder
                .verify(playersWithStatisticsRepository, Mockito.times(1))
                .getTheBestPlayer();

        inOrder
                .verify(fromPlayerWithStatisticToPlayerData, Mockito.times(1))
                .toList(ArgumentMatchers.anyList());
    }

    @Test
    @DisplayName("When finding the best player in a period and the 'date from' is null")
    public void test6() {

        Assertions.assertThatThrownBy(() ->
                        playerService.getTheBestPlayerInPeriod(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Start date cannot be null");

    }

    @Test
    @DisplayName("When finding the best player in a period and the 'date to' is null")
    public void test7() {

        Assertions.assertThatThrownBy(() ->
                        playerService.getTheBestPlayerInPeriod(LocalDateTime.parse("2024-01-01T10:00:00"), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("End date cannot be null");
    }

    @Test
    @DisplayName("When finding the best player in a period and the 'date to' is after the 'date end'")
    public void test8() {

        Assertions.assertThatThrownBy(() ->
                        playerService.getTheBestPlayerInPeriod(
                                LocalDateTime.parse("2024-02-01T10:00:00"),
                                LocalDateTime.parse("2024-01-01T10:00:00")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Start date cannot be after end date");
    }

    @Test
    @DisplayName("When finding the best player in a period")
    public void test9() {

        Mockito.when(playersWithStatisticsRepository
                        .getTheBestPlayerInPeriod(
                                ArgumentMatchers.any(LocalDateTime.class),
                                ArgumentMatchers.any(LocalDateTime.class)))
                .thenReturn(List.of(PLAYER_WITH_STATISTIC_1));

        Mockito.when(fromPlayerWithStatisticToPlayerData
                        .toList(ArgumentMatchers.anyList()))
                .thenReturn(List.of(PLAYER_DATA_2));

        Assertions.assertThat(playerService.getTheBestPlayerInPeriod(
                        LocalDateTime.parse("2023-12-30T22:00:00"),
                        LocalDateTime.parse("2024-01-02T12:00:34")))
                .isEqualTo(List.of(PLAYER_DATA_2));

        var inOrder = Mockito
                .inOrder(playersWithStatisticsRepository, fromPlayerWithStatisticToPlayerData);

        inOrder
                .verify(playersWithStatisticsRepository, Mockito.times(1))
                .getTheBestPlayerInPeriod(
                        ArgumentMatchers.any(LocalDateTime.class),
                        ArgumentMatchers.any(LocalDateTime.class));

        inOrder
                .verify(fromPlayerWithStatisticToPlayerData, Mockito.times(1))
                .toList(ArgumentMatchers.anyList());
    }

    @Test
    @DisplayName("When getting the ranking of players")
    public void test10() {

        Mockito.when(playerRepository.getPlayersRanking())
                .thenReturn(Map.of(100,"Jony"));

        Assertions.assertThat(playerService.getRankingOfPlayers())
                .isEqualTo(Map.of(100,"Jony"));

        Mockito.verify(playerRepository, Mockito.times(1))
                .getPlayersRanking();
    }
}