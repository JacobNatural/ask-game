package com.app.service;

import com.app.data_provider.DataProvider;
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
import java.util.List;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplSavePlayerToTxtTest {
    @Mock
    private PlayerRepository<Player> playerRepository;

    @Mock
    private FileWriter<Player> fileWriter;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    @DisplayName("When the function is null")
    public void test1(){

        Assertions.assertThatThrownBy(() ->
                        playerService.savePlayerToTxt(DataProvider.FILENAME_SAVE, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ToTxtFormat cannot be null");
    }

    @Test
    @DisplayName("When saving the players to TXT")
    public void test2(){

        Mockito.when(playerRepository.getPlayers())
                .thenReturn(List.of(PLAYER1,PLAYER2, PLAYER3));

        Assertions.assertThatNoException().isThrownBy(
                () -> playerService.savePlayerToTxt(DataProvider.FILENAME_SAVE,
                        Player::toTxtFormat));

        Mockito.verify(playerRepository, Mockito.times(1)).getPlayers();

        Mockito.verify(fileWriter, Mockito.times(1))
                .save(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.eq(List.of(PLAYER1, PLAYER2, PLAYER3)),
                        ArgumentMatchers.any());
    }
}
