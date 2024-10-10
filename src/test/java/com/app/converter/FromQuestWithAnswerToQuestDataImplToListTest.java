package com.app.converter;

import com.app.converter.impl.FromQuestWithAnswerToQuestDataImpl;
import com.app.extension.converter.FromQuestWithAnswerToQuestDataImplExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import static com.app.converter.ConverterTestData.*;

@ExtendWith(FromQuestWithAnswerToQuestDataImplExtension.class)
@RequiredArgsConstructor
public class FromQuestWithAnswerToQuestDataImplToListTest {

    private final FromQuestWithAnswerToQuestDataImpl fromQuestWithAnswerToQuestData;

    @Test
    @DisplayName("When converting from a QuestWithAnswer list to a QuestData list")
    public void test1(){
        Assertions.assertThat(fromQuestWithAnswerToQuestData
                .toList(QUEST_WITH_ANSWERS_1))
                .hasSize(2)
                .contains(QUEST_DATA_1, QUEST_DATA_2);
    }
}
