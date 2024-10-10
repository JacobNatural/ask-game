package com.app.converter;


import com.app.converter.impl.FromQuestWithAnswerToQuestDataImpl;
import com.app.extension.converter.FromQuestWithAnswerToQuestDataImplExtension;
import com.app.model.difficulty_level.DifficultyLevel;
import com.app.repository.model.Quest;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.List;
import java.util.Map;

import static com.app.converter.ConverterTestData.*;

@ExtendWith(FromQuestWithAnswerToQuestDataImplExtension.class)
@RequiredArgsConstructor
public class FromQuestWithAnswerToQuestDataImplToGroupByTest {
    private final FromQuestWithAnswerToQuestDataImpl fromQuestWithAnswerToQuestData;

    @Test
    @DisplayName("When converting from a QuestWithAnswer list to grouping by and converting to a QuestData list")
    public void test1() {

        Assertions.assertThat(fromQuestWithAnswerToQuestData
                        .toGroupBy(QUEST_WITH_ANSWERS_2, Quest::getDifficultyLevel))
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(
                        Map.ofEntries(
                                Map.entry(DifficultyLevel.A, List.of(QUEST_DATA_2)),
                                Map.entry(DifficultyLevel.B, List.of(QUEST_DATA_1,QUEST_DATA_3))));

    }
}
