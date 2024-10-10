package com.app.model.quest;


import com.app.repository.model.Quest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.app.model.ModelTestData.*;

class QuestDataToQuestTest {
    static Stream<Arguments> dataProvide(){
        return Stream.of(
                Arguments.of(QUEST_DATA_1, QUEST_1),
                Arguments.of(QUEST_DATA_2, QUEST_2),
                Arguments.of(QUEST_DATA_3, QUEST_3)
        );
    }

    @ParameterizedTest
    @DisplayName("When creating a quest")
    @MethodSource("dataProvide")
    public void test1(QuestData questData, Quest quest){
        Assertions.assertThat(questData.toQuest())
                .isEqualTo(quest);
    }
}
