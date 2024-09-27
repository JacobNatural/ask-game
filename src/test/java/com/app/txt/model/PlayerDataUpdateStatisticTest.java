package com.app.txt.model;

import com.app.extension.txt.model.PlayerDataExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@ExtendWith(PlayerDataExtension.class)
public class PlayerDataUpdateStatisticTest {

    private final PlayerData playerData;

    @Test
    @DisplayName("When the player's statistic is updated")
    public void test1() {
        var newStatistic =
                Map.of(LocalDateTime.parse("2024-03-30T22:22:22"), 100);
        playerData.updateStatistic(newStatistic);


        Assertions.assertThat(playerData)
                .isEqualTo(new PlayerData("Jakub", Map.of(
                        LocalDateTime.parse("2024-01-01T22:00"),60,
                        LocalDateTime.parse("2024-03-30T22:22:22"), 100)));
    }

    @Test
    @DisplayName("When the player's statistic with the existing date is updated")
    public void test2() {
        var newStatistic =
                Map.of(LocalDateTime.parse("2024-01-01T22:00"),90);
        playerData.updateStatistic(newStatistic);


        Assertions.assertThat(playerData)
                .isEqualTo(new PlayerData("Jakub", Map.of(
                        LocalDateTime.parse("2024-01-01T22:00"),60)));
    }
}
