package com.app.repository.impl;

import com.app.data_provider.DataProvider;
import com.app.extension.txt.load.QuestLoadExtension;
import com.app.extension.repository.QuestRepositoryExtension;
import com.app.txt.load.impl.QuestFileReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Paths;
import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith({QuestRepositoryExtension.class, QuestLoadExtension.class})
@RequiredArgsConstructor
public class QuestRepositoryImplGetAllTest {
    private final QuestRepositoryImpl questRepositoryImpl;
    private final QuestFileReader questLoad;

    @Test
    @DisplayName("When the repository contains correct data")
    public void test1() {
        System.out.println(questRepositoryImpl.getAll());
        Assertions.assertThat(questRepositoryImpl.getAll())
                .containsValues(QUEST1, QUEST2, QUEST3);
    }

    @Test
    @DisplayName("When the repository reads data from an empty text file")
    @SneakyThrows
    public void test2() {
        var filename = Paths.get(
                DataProvider.class.getClassLoader().getResource(FILENAME_EMPTY_FILE).toURI()).toString();
        var questRepository2 = new QuestRepositoryImpl(filename, questLoad);

        Assertions.assertThat(questRepository2.getAll())
                .isEqualTo(Map.of());
    }
}
