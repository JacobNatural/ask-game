package com.app.quest;

import com.app.difficulty_level.DifficultyLevel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.data_provider.DataProvider.QUEST1;

public class QuestMapperToDifficultyTest {

    @Test
    @DisplayName("When mapping a Quest object to a difficulty level, the correct question should be returned")
    public void test1() {
        Assertions.assertThat(QuestMapper.toDifficulty.apply(QUEST1))
                .isEqualTo(DifficultyLevel.A);
    }
}
