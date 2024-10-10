package com.app.converter;

import com.app.converter.impl.FromQuestWithAnswerToQuestDataImpl;
import com.app.extension.converter.FromQuestWithAnswerToQuestDataImplExtension;
import com.app.repository.model.Quest;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static com.app.converter.ConverterTestData.*;

@ExtendWith(FromQuestWithAnswerToQuestDataImplExtension.class)
@RequiredArgsConstructor
public class FromQuestWithAnswerToQuestDataImplToMapTest {
    private final FromQuestWithAnswerToQuestDataImpl fromQuestWithAnswerToQuestData;

    @Test
    @DisplayName("When converting from a QuestWithAnswer list to a QuestData map")
    public void test1() {
        Assertions.assertThat(fromQuestWithAnswerToQuestData
                        .toMap(QUEST_WITH_ANSWERS_1, Quest::getId)
                ).hasSize(2)
                .containsExactlyInAnyOrderEntriesOf(Map.ofEntries(
                        Map.entry(1L, QUEST_DATA_1),
                        Map.entry(2L, QUEST_DATA_2)));
    }
}
