package com.app.model.quest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.model.ModelTestData.QUEST_DATA_1;
import static com.app.model.ModelTestData.QUEST_DTO_1;


public class QuestCreateQuestDataDtoTest {

    @Test
    @DisplayName("When the index is created for the DTO")
    public void test1(){
        Assertions.assertThat(QUEST_DATA_1.createQuestDto())
                .isEqualTo(QUEST_DTO_1);
    }
}
