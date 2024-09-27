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

import static com.app.data_provider.DataProvider.FILENAME_EMPTY_FILE;
@RequiredArgsConstructor
@ExtendWith({PlayerRepositoryExtension.class, PlayerLoadExtension.class})
public class PlayerRepositoryImplContainsIdTest {
    private final PlayerRepositoryImpl playerRepository;
    private final PlayerFileReader playerLoad;

    @Test
    @DisplayName("When the repository contains an ID")
    public void test1(){
        playerRepository.getAll().entrySet().forEach(System.out::println);
        Assertions.assertThat(playerRepository.containsID(1L))
                .isTrue();
    }

    @Test
    @DisplayName("When the repository doesn't contain an ID")
    public void test2(){
        Assertions.assertThat(playerRepository.containsID(1202L))
                .isFalse();
    }

    @Test
    @DisplayName("When the repository is empty")
    @SneakyThrows
    public void test3(){
        var filename = Paths.get(
                PlayerRepositoryImplContainsIdTest.class.getClassLoader().getResource(FILENAME_EMPTY_FILE).toURI()).toString();
        var playerRepository2 = new PlayerRepositoryImpl(filename,playerLoad);

        Assertions.assertThat(playerRepository2.containsID(1L))
                .isFalse();
    }
}
