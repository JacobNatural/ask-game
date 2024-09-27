package com.app.quest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.data_provider.DataProvider.QUEST1;
import static com.app.data_provider.DataProvider.QUEST_DTO_1;

public class QuestCreateQuestDtoTest {

    @Test
    @DisplayName("When the index is created for the DTO")
    public void test1(){
        Assertions.assertThat(QUEST1.createQuestDto())
                .isEqualTo(QUEST_DTO_1);
    }
}
