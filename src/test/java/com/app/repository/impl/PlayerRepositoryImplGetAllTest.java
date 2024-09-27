package com.app.repository.impl;

import com.app.extension.txt.load.PlayerLoadExtension;
import com.app.extension.repository.PlayerRepositoryExtension;
import com.app.txt.load.impl.PlayerFileReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Paths;
import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith({PlayerRepositoryExtension.class, PlayerLoadExtension.class})
@RequiredArgsConstructor
public class PlayerRepositoryImplGetAllTest {
    private final PlayerRepositoryImpl playerRepositoryImpl;
    private final PlayerFileReader playerLoad;

    @Test
    @DisplayName("When the repository contains correct data")
    public void test1(){
        Assertions.assertThat(playerRepositoryImpl.getAll().values())
                .containsExactlyInAnyOrder(PLAYER2, PLAYER3, PLAYER4);
    }


    @Test
    @DisplayName("When the repository reads data from an empty text file")
    @SneakyThrows
    public void test2(){
        var filename = Paths.get(
                PlayerRepositoryImplGetAllTest.class.getClassLoader().getResource(FILENAME_EMPTY_FILE).toURI()).toString();
                var playerRepository2 = new PlayerRepositoryImpl(filename,playerLoad);

        Assertions.assertThat(playerRepository2.getAll())
                .isEqualTo(Map.of());
    }
}
