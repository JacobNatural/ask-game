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

import java.util.List;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplGetTheBestPlayerTest {
    @Mock
    private PlayerRepository<Player> playerRepository;

    @Mock
    private FileWriter<Player> fileWriter;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    @DisplayName("When the repository is empty")
    public void test1() {

        Mockito.when(playerRepository.getPlayers())
                .thenReturn(List.of());

        Assertions.assertThatThrownBy(() ->
                        playerService.getTheBestPlayer())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Repository is empty");

        Mockito.verify(playerRepository, Mockito.times(1)).getPlayers();
    }

    @Test
    @DisplayName("When you get the best player")
    public void test2() {

        Mockito.when(playerRepository.getPlayers())
                .thenReturn(List.of(PLAYER1, PLAYER2, PLAYER3));

        Assertions.assertThat(playerService.getTheBestPlayer())
                .isEqualTo(List.of(PLAYER2));

        Mockito.verify(playerRepository, Mockito.times(1)).getPlayers();
    }

    @Test
    @DisplayName("When we have two of the best players")
    public void test3() {

        Mockito.when(playerRepository.getPlayers())
                .thenReturn(List.of( PLAYER3, PLAYER4, PLAYER5 ));

        Assertions.assertThat(playerService.getTheBestPlayer())
                .isEqualTo(List.of(PLAYER3,PLAYER4));

        Mockito.verify(playerRepository, Mockito.times(1)).getPlayers();
    }

}
