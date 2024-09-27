package com.app.quest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.data_provider.DataProvider.QUEST1;

public class QuestMapperToQuestionTest {
    @Test
    @DisplayName("When mapping a Quest object to a question string, the correct question should be returned")
    public void test1() {
        Assertions.assertThat(QuestMapper.toQuestion.apply(QUEST1))
                .isEqualTo("Popular phone in the USA?");
    }
}
