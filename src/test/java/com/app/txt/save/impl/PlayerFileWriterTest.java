package com.app.txt.save.impl;

import com.app.extension.txt.save.PlayerSaveExtension;
import com.app.player.Player;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(PlayerSaveExtension.class)
@RequiredArgsConstructor
public class PlayerFileWriterTest {
    private final PlayerFileWriter playerSave;

    @Test
    @DisplayName("When saving a player to a text file")
    @SneakyThrows
    public void test1(){
        playerSave.save(FILENAME_SAVE, List.of(PLAYER1), Player::toTxtFormat);

        try(var lines = Files.lines(Paths.get(FILENAME_SAVE))){
            Assertions.assertThat(
                    lines.collect(Collectors.joining("\n")))
                    .isEqualTo(Player.toTxtFormat(PLAYER1));
        }
    }

    @AfterAll
    @SneakyThrows
    public static void afterAll(){
        Files.deleteIfExists(Paths.get(FILENAME_SAVE));
    }
}
