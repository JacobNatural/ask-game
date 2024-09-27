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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PlayerServiceImplGetRankingOfPlayersTest {
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

        Assertions.assertThat(playerService.getRankingOfPlayers())
                .isEqualTo(Map.of());

        Mockito.verify(playerRepository, Mockito.times(1))
                .getPlayers();
    }

    @Test
    @DisplayName("When we get the player's rankings")
    public void test2() {

        Mockito.when(playerRepository.getPlayers())
                .thenReturn(List.of(PLAYER1, PLAYER2,
                        PLAYER3, PLAYER7));

        Assertions.assertThat(playerService.getRankingOfPlayers())
                .containsExactlyInAnyOrderEntriesOf(Map.ofEntries(
                        Map.entry(150, Set.of("Jakub", "Halina")),
                        Map.entry(90, Set.of("Szynszyl")),
                        Map.entry(130, Set.of("Darek"))));


        Mockito.verify(playerRepository, Mockito.times(1))
                .getPlayers();
    }
}
