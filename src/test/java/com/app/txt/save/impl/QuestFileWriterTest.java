package com.app.txt.save.impl;

import com.app.extension.txt.save.QuestSaveExtension;
import com.app.quest.Quest;
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

@ExtendWith(QuestSaveExtension.class)
@RequiredArgsConstructor
public class QuestFileWriterTest {
    private final QuestFileWriter questSave;

    @Test
    @DisplayName("When saving a quest to a text file")
    @SneakyThrows
    public void test1(){
        questSave.save(FILENAME_SAVE, List.of(QUEST1), Quest::toTxtFormat);

        try(var lines = Files.lines(Paths.get(FILENAME_SAVE))){
            Assertions.assertThat(
                            lines.collect(Collectors.joining("\n")))
                    .isEqualTo(Quest.toTxtFormat(QUEST1));
        }
    }

    @AfterAll
    @SneakyThrows
    public static void afterAll(){
        Files.deleteIfExists(Paths.get(FILENAME_SAVE));
    }
}
