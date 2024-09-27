package com.app.service;

import com.app.game.service.impl.PlayerServiceImpl;
import com.app.player.Player;
import com.app.repository.PlayerRepository;
import com.app.txt.save.FileWriter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplRemovePlayerTest {
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
                        playerService.removePlayer(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Player not found");

        Mockito.verify(playerRepository, Mockito.times(1))
                .containsID(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("When the player is removed by ID")
    public void test2(){
        Mockito.when(playerRepository.containsID(ArgumentMatchers.anyLong()))
                .thenReturn(true);

        Assertions.assertThatNoException().isThrownBy(
                () -> playerService.removePlayer(1L));

        InOrder inOrder = Mockito.inOrder(playerRepository);

        inOrder.verify(playerRepository, Mockito.times(1))
                .containsID(ArgumentMatchers.anyLong());

        inOrder.verify(playerRepository, Mockito.times(1))
                .removeByID(ArgumentMatchers.anyLong());
    }
}
