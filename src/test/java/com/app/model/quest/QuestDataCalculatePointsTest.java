package com.app.model.quest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.app.model.ModelTestData.*;

public class QuestDataCalculatePointsTest {

    static Stream<Arguments> provideData1(){
        return Stream.of(
                Arguments.of(QUEST_DATA_1, "Georges Bizet", 10),
                Arguments.of(QUEST_DATA_2, "Baltic Sea", 10),
                Arguments.of(QUEST_DATA_3, "Vistula",15)
        );
    }

    @ParameterizedTest
    @MethodSource("provideData1")
    @DisplayName("When the answer is correct")
    public void test1(QuestData questData, String answer, int points){
        Assertions.assertThat(questData.calculatePoints(answer))
                .isEqualTo(points);
    }

    static Stream<Arguments> provideData2(){
        return Stream.of(
                Arguments.of(QUEST_DATA_1, "Johann Strauss II", 0),
                Arguments.of(QUEST_DATA_2, "Black Sea", 0),
                Arguments.of(QUEST_DATA_3, "Warta",0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideData2")
    @DisplayName("When the answer is incorrect")
    public void test2(QuestData questData, String answer, int points){
        Assertions.assertThat(questData.calculatePoints(answer))
                .isEqualTo(points);
    }
}
