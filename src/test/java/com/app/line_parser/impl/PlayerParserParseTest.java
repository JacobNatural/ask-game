package com.app.line_parser.impl;

import com.app.extension.txt.line_parser.PlayerParserExtension;
import com.app.player.Player;
import com.app.txt.model.PlayerData;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

@ExtendWith(PlayerParserExtension.class)
@RequiredArgsConstructor
public class PlayerParserParseTest {
    private final PlayerParser playerParser;

    @Test
    @DisplayName("When the line is null")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                playerParser.parse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be null");
    }

    @Test
    @DisplayName("When the line is empty")
    public void test2(){
        Assertions.assertThatThrownBy(() ->
                        playerParser.parse(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be empty");
    }

    @Test
    @DisplayName("When the line does not match regex")
    public void test3(){
        Assertions.assertThatThrownBy(() ->
                        playerParser.parse("wrong line"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(STR."Line does not match regex: wrong line");
    }

    @Test
    @DisplayName("When the line contains an invalid date")
    public void test4(){
        Assertions.assertThatThrownBy(() ->
                        playerParser.parse("Szynszyl;2024-04-46T14:03:08;90"))
                .isInstanceOf(DateTimeParseException.class);
    }

    @Test
    @DisplayName("When the line contains an invalid time")
    public void test5(){
        Assertions.assertThatThrownBy(() ->
                        playerParser.parse("Szynszyl;2024-04-26T25:03:08;90"))
                .isInstanceOf(DateTimeParseException.class);
    }

    @Test
    @DisplayName("When the line is correct and creates a Player")
    public void test6(){
        Assertions.assertThat(playerParser.parse("Szynszyl;2024-04-26T20:03:08;90"))
                .isEqualTo(new PlayerData("Szynszyl",
                        Map.of(LocalDateTime.parse("2024-04-26T20:03:08"), 90)));
    }
}
