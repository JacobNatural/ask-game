package com.app.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.stream.Stream;

import static com.app.data_provider.DataProvider.PLAYER1;
import static com.app.data_provider.DataProvider.PLAYER6;

public class PlayerFilterByPeriodTest {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(
                        LocalDateTime.parse("2022-01-01T22:00"),
                        LocalDateTime.parse("2022-01-20T22:00") ),
                Arguments.of(
                        LocalDateTime.parse("2024-05-01T22:00"),
                        LocalDateTime.parse("2024-06-20T22:00") )
                );

    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    @DisplayName("When there is no player during the specified period")
    public void test1(LocalDateTime from, LocalDateTime to){

        Assertions.assertThat(PLAYER1.filterByPeriod(
                from, to))
                .isNull();
    }

    @Test
    @DisplayName("When there is a player and all statistics are within this period")
    public void test2(){
        Assertions.assertThat(PLAYER1.filterByPeriod(
                        LocalDateTime.parse("2021-12-01T22:00"), LocalDateTime.parse("2022-03-20T22:00")))
                .isEqualTo(PLAYER1);
    }

    @Test
    @DisplayName("When there is a player and not all statistics are within this period")
    public void test3(){

        Assertions.assertThat(PlayerMapper.toStatistic.apply(PLAYER6.filterByPeriod(
                        LocalDateTime.parse("2024-04-01T22:00"), LocalDateTime.parse("2024-04-30T13:00"))))
                .isEqualTo(Map.of(
                        LocalDateTime.of(
                                LocalDate.of(2024, 4, 29),
                                LocalTime.of(18, 20, 23)
                        ), 90));
    }
}