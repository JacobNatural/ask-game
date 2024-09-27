package com.app.repository.impl;

import com.app.extension.repository.PlayerRepositoryExtension;
import com.app.extension.txt.load.PlayerLoadExtension;
import com.app.txt.load.impl.PlayerFileReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Paths;
import java.util.List;

import static com.app.data_provider.DataProvider.*;

@ExtendWith({PlayerRepositoryExtension.class, PlayerLoadExtension.class})
@RequiredArgsConstructor
public class PlayerRepositoryImplGetPlayersTest {
    private final PlayerRepositoryImpl playerRepository;
    private final PlayerFileReader playerLoad;

    @Test
    @DisplayName("When the player is retrieved from the repository")
    public void test1(){
        Assertions.assertThat(playerRepository.getPlayers())
                .contains(PLAYER4,PLAYER3, PLAYER2);
    }

    @Test
    @DisplayName("When the repository reads data from an empty text file")
    @SneakyThrows
    public void test2(){
        var filename = Paths.get(
                PlayerRepositoryImplGetPlayersTest.class.getClassLoader().getResource(FILENAME_EMPTY_FILE).toURI()).toString();
        var playerRepository2 = new PlayerRepositoryImpl(filename,playerLoad);

        Assertions.assertThat(playerRepository2.getPlayers())
                .isEqualTo(List.of());
    }
}
