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

import static com.app.data_provider.DataProvider.PLAYER1;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplAddPlayerTest {

    @Mock
    private PlayerRepository<Player> playerRepository;

    @Mock
    private FileWriter<Player> fileWriter;

    @InjectMocks
    private PlayerServiceImpl playerService;


    @Test
    @DisplayName("When the quest is null")
    public void test1() {

        Assertions.assertThatThrownBy(() ->
                        playerService.addPlayer(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Player cannot be null");
    }

    @Test
    @DisplayName("When the player contain object")
    public void test2() {

        Mockito.when(playerRepository.containsValue(PLAYER1))
                .thenReturn(true);

        Assertions.assertThatThrownBy(() ->
                        playerService.addPlayer(PLAYER1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Player already exists");

        Mockito.verify(playerRepository, Mockito.times(1)).containsValue(PLAYER1);
    }

    @Test
    @DisplayName("When add player to repository")
    public void test3() {

        Assertions.assertThatNoException().isThrownBy(() ->
                playerService.addPlayer(PLAYER1));

        InOrder inOrder = Mockito.inOrder(playerRepository);

        inOrder.verify(playerRepository, Mockito.times(1))
                .containsValue(ArgumentMatchers.any(Player.class));

        Mockito.verify(playerRepository, Mockito.times(1))
                .addPlayer(ArgumentMatchers.any(Player.class));
    }


}
