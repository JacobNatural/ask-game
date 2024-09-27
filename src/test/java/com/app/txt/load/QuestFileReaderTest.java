package com.app.txt.load;

import com.app.extension.txt.load.QuestLoadExtension;
import com.app.txt.load.impl.QuestFileReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(QuestLoadExtension.class)
@RequiredArgsConstructor
public class QuestFileReaderTest {
    private final QuestFileReader questLoad;

    @Test
    @DisplayName("When reading a player from the text file")
    @SneakyThrows
    public void test1() {
        Assertions.assertThat(questLoad.load(getPath(FILENAME_QUEST)))
                .isEqualTo(List.of(QUEST_DATA1));
    }
}
