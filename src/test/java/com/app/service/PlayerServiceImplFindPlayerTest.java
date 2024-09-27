package com.app.service;

import com.app.game.service.impl.PlayerServiceImpl;
import com.app.player.Player;
import com.app.repository.PlayerRepository;
import com.app.txt.save.FileWriter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.app.data_provider.DataProvider.PLAYER1;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplFindPlayerTest {

    @Mock
    private PlayerRepository<Player> playerRepository;

    @Mock
    private FileWriter<Player> fileWriter;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    @DisplayName("When the player does not exist")
    public void test1(){

        Mockito.when(playerRepository.containsID(ArgumentMatchers.anyLong()))
                .thenReturn(false);

        Assertions.assertThatThrownBy(() ->
                        playerService.findPlayer(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Player not found");

        Mockito.verify(playerRepository,Mockito.times(1))
                .containsID(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("When the player is found by ID")
    public void test2(){

        Mockito.when(playerRepository.containsID(ArgumentMatchers.anyLong()))
                .thenReturn(true);

        Mockito.when(playerRepository.findByID(1L))
                .thenReturn(PLAYER1);

        Assertions.assertThat(playerService.findPlayer(1L))
                .isEqualTo(PLAYER1);

        var inOrder = Mockito.inOrder(playerRepository);

        inOrder.verify(playerRepository, Mockito.times(1))
                .containsID(1L);

        inOrder.verify(playerRepository, Mockito.times(1))
                .findByID(1L);
    }
}
