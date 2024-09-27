package com.app.txt.load;

import com.app.extension.txt.load.PlayerLoadExtension;
import com.app.txt.load.impl.PlayerFileReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(PlayerLoadExtension.class)
@RequiredArgsConstructor
public class PlayerFileReaderTest {
    private final PlayerFileReader playerLoad;

    @Test
    @DisplayName("When reading a player from the text file")
    @SneakyThrows
    public void test1() {
        Assertions.assertThat(playerLoad.load(getPath(FILENAME_PLAYER)))
                .isEqualTo(List.of(PLAYER_DATA1));
    }
}

