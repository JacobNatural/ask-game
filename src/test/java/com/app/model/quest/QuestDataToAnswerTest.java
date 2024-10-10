package com.app.model.quest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.model.ModelTestData.ANSWERS_1;
import static com.app.model.ModelTestData.QUEST_DATA_1;

public class QuestDataToAnswerTest {

    @Test
    @DisplayName("When creating a answers")
    public void test1(){
        Assertions.assertThat(QUEST_DATA_1.toAnswer())
                .hasSameElementsAs(ANSWERS_1);
    }
}
