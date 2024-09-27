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
public class QuestRepositoryImplFindByIDTest {
    private final QuestRepositoryImpl questRepository;

    @Test
    @DisplayName("When find quest by ID")
    public void test1() {
        Assertions.assertThat(questRepository.findByID(1L))
                .isEqualTo(QUEST2);
    }
}
