package com.app.quest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.data_provider.DataProvider.QUEST1;

public class QuestContainAnswerTest {

    @Test
    @DisplayName("When the quest contain answer")
    public void test1(){
        Assertions.assertThat(QUEST1.containAnswer("iPhone"))
                .isTrue();
    }

    @Test
    @DisplayName("When the quest does not contain answer")
    public void test2(){
        Assertions.assertThat(QUEST1.containAnswer("Siemens"))
                .isFalse();
    }
}
