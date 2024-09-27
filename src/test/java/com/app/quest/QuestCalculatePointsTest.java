package com.app.quest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.app.data_provider.DataProvider.*;

public class QuestCalculatePointsTest {

    static Stream<Arguments> provideData1(){
        return Stream.of(
                Arguments.of(QUEST1, "iPhone", 10),
                Arguments.of(QUEST2, "Baltic Sea", 10),
                Arguments.of(QUEST3, "Vistula",15)
        );
    }

    @ParameterizedTest
    @MethodSource("provideData1")
    @DisplayName("When the answer is correct")
    public void test1(Quest quest, String answer, int points){
        Assertions.assertThat(quest.calculatePoints(answer))
                .isEqualTo(points);
    }

    static Stream<Arguments> provideData2(){
        return Stream.of(
                Arguments.of(QUEST1, "Nokia", 0),
                Arguments.of(QUEST2, "Black Sea", 0),
                Arguments.of(QUEST3, "Warta",0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideData2")
    @DisplayName("When the answer is incorrect")
    public void test2(Quest quest, String answer, int points){
        Assertions.assertThat(quest.calculatePoints(answer))
                .isEqualTo(points);
    }
}
