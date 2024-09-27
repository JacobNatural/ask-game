package com.app.quest;

import com.app.difficulty_level.DifficultyLevel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.app.data_provider.DataProvider.*;
import static com.app.data_provider.DataProvider.QUEST4;

public class QuestHasDifficultLevelTest {

    static Stream<Arguments> provideDataWithTrue() {
        return Stream.of(
                Arguments.of(QUEST1, DifficultyLevel.A, true),
                Arguments.of(QUEST3, DifficultyLevel.B, true),
                Arguments.of(QUEST4, DifficultyLevel.B, true)
        );
    }

    @ParameterizedTest
    @DisplayName("When the quest contains a difficulty level")
    @MethodSource("provideDataWithTrue")
    public void test1(Quest quest, DifficultyLevel difficultyLevel, boolean expected) {

        Assertions.assertThat(quest.hasDifficultLevel(difficultyLevel))
                .isEqualTo(expected);
    }

    static Stream<Arguments> provideDataWithFalse() {
        return Stream.of(
                Arguments.of(QUEST1, DifficultyLevel.B, false),
                Arguments.of(QUEST3, DifficultyLevel.C, false),
                Arguments.of(QUEST4, DifficultyLevel.A, false)
        );
    }

    @ParameterizedTest
    @DisplayName("When the quest does not contain a difficulty level")
    @MethodSource("provideDataWithFalse")
    public void test2(Quest quest, DifficultyLevel difficultyLevel, boolean expected) {

        Assertions.assertThat(quest.hasDifficultLevel(difficultyLevel))
                .isEqualTo(expected);
    }
}
