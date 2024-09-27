package com.app.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.app.data_provider.DataProvider.*;

public class PlayerGetTheBestScoreTest {

    static Stream<Arguments> provideData(){
        return Stream.of(
                Arguments.of(PLAYER2,150),
                Arguments.of(PLAYER6, 90),
                Arguments.of(PLAYER7, 150)
        );
    }

    @ParameterizedTest
    @DisplayName("When getting the best score")
    @MethodSource("provideData")
    public void test1(Player player, int score){
        Assertions.assertThat(player.getTheBestScore())
                .isEqualTo(score);
    }
}
