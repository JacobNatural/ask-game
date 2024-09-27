package com.app.repository.impl;

import com.app.extension.repository.QuestRepositoryExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(QuestRepositoryExtension.class)
@RequiredArgsConstructor
public class QuestRepositoryImplAddObjectTest {
    private final QuestRepositoryImpl questRepositoryImpl;

    @Test
    @DisplayName("When adding a new quest to the repository")
    public void test1() {
        questRepositoryImpl.addQuest(QUEST4);

        Assertions.assertThat(questRepositoryImpl.getAll())
                .containsValue(QUEST4);
    }
}
