package com.app.player;

import com.app.extension.player.PlayerExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(PlayerExtension.class)
@RequiredArgsConstructor
public class PlayerUpdateStatisticTest {
    private final Player player;

    @Test
    @DisplayName("When update statistic for player")
    public void test1() {

        player.updateStatistic(PlayerMapper.toStatistic.apply(PLAYER5));

        Assertions.assertThat(PlayerMapper.toStatistic.apply(player))
                .isEqualTo(PlayerMapper.toStatistic.apply(PLAYER6));
    }

    @Test
    @DisplayName("When updating statistics for a player, but the date already exists, leave the old data")
    public void test2() {

        player.updateStatistic(Map.of(
                LocalDateTime.of(
                        LocalDate.of(2024, 4, 29),
                        LocalTime.of(18, 20, 23)
                ), 110
        ));

        Assertions.assertThat(PlayerMapper.toStatistic.apply(PLAYER4))
                .isEqualTo(Map.of(
                        LocalDateTime.of(
                                LocalDate.of(2024, 4, 29),
                                LocalTime.of(18, 20, 23)
                        ), 90
                ));
    }
}
