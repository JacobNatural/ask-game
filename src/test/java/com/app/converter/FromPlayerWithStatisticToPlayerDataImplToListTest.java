package com.app.converter;

import com.app.converter.impl.FromPlayerWithStatisticToPlayerDataImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.app.converter.ConverterTestData.*;

public class FromPlayerWithStatisticToPlayerDataImplToListTest {

    @Test
    @DisplayName("When converting from a PlayerWithStatistic list to a PlayerData list")
    public void test1() {
        var fromPlayerWithStatisticToPlayerData = new FromPlayerWithStatisticToPlayerDataImpl();
        Assertions
                .assertThat(fromPlayerWithStatisticToPlayerData
                        .toList(List.of(
                                        PLAYER_WITH_STATISTIC_1,
                                        PLAYER_WITH_STATISTIC_2,
                                        PLAYER_WITH_STATISTIC_3
                                )
                        ))
                .hasSize(2)
                .contains(PLAYER_DATA_1, PLAYER_DATA_2);
    }
}
