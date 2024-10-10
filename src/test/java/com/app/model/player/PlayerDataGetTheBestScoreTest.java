package com.app.model.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static com.app.model.ModelTestData.*;

public class PlayerDataGetTheBestScoreTest {

    static Stream<Arguments> provideData(){
        return Stream.of(
                Arguments.of(PLAYER_DATA_1,85),
                Arguments.of(PLAYER_DATA_2, 110),
                Arguments.of(PLAYER_DATA_3, 105)
        );
    }

    @ParameterizedTest
    @DisplayName("When getting the best score")
    @MethodSource("provideData")
    public void test1(PlayerData playerData, int score){
        Assertions.assertThat(playerData.getTheBestScore())
                .isEqualTo(score);
    }
}
