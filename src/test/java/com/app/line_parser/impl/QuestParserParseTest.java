package com.app.line_parser.impl;

import com.app.data_provider.DataProvider;
import com.app.extension.txt.line_parser.QuestParserExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(QuestParserExtension.class)
@RequiredArgsConstructor
public class QuestParserParseTest {
    private final QuestParser questParser;

    @Test
    @DisplayName("When the line is null")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                        questParser.parse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be null");
    }

    @Test
    @DisplayName("When the line is empty")
    public void test2(){
        Assertions.assertThatThrownBy(() ->
                        questParser.parse(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Line cannot be empty");
    }

    @Test
    @DisplayName("When the line does not match regex")
    public void test3(){
        Assertions.assertThatThrownBy(() ->
                        questParser.parse("wrong line"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(STR."Line does not match regex: wrong line");
    }

    @Test
    @DisplayName("When we don't have a correct answer")
    public void test4(){
        Assertions.assertThatThrownBy(() ->
                        questParser.parse("A;science;Popular phone in the USA?;Nokia-false;iPhone-false;Android-false;"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Amount of correct answer is incorrect");
    }

    @Test
    @DisplayName("When the correct answers are too many")
    public void test5(){
        Assertions.assertThatThrownBy(() ->
                        questParser.parse("A;science;Popular phone in the USA?;Nokia-true;iPhone-true;Android-false;"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Amount of correct answer is incorrect");
    }

    @Test
    @DisplayName("When the line is correct and creates a Quest")
    public void test6(){
        Assertions.assertThat(
                questParser.parse(
                        "A;science;Popular phone in the USA?;Nokia-false;iPhone-true;Android-false;"))
                .isEqualTo(DataProvider.QUEST_DATA1);
    }
}
