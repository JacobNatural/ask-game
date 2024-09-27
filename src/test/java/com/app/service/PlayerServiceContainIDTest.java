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

@ExtendWith(MockitoExtension.class)
public class PlayerServiceContainIDTest {

    @Mock
    private PlayerRepository<Player> playerRepository;

    @Mock
    private FileWriter<Player> fileWriter;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    @DisplayName("When player repository contain ID")
    public void test1(){

        Mockito.when(playerRepository.containsID(ArgumentMatchers.anyLong()))
                .thenReturn(true);

        Assertions.assertThat(playerService.containID(1L))
                .isTrue();

        Mockito.verify(playerRepository, Mockito.times(1))
                .containsID(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("When player repository contain ID")
    public void test2(){

        Mockito.when(playerRepository.containsID(ArgumentMatchers.anyLong()))
                .thenReturn(false);

        Assertions.assertThat(playerService.containID(1L))
                .isFalse();

        Mockito.verify(playerRepository, Mockito.times(1))
                .containsID(ArgumentMatchers.anyLong());
    }
}
