package com.app.repository.impl;

import com.app.extension.repository.QuestRepositoryExtension;
import com.app.repository.QuestRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(QuestRepositoryExtension.class)
@RequiredArgsConstructor
public class QuestRepositoryImplRemoveByIdTest {
    private final QuestRepositoryImpl questRepository;

    @Test
    @DisplayName("When removing an ID from the quest repository")
    @SneakyThrows
    public void test1(){
        questRepository.removeByID(1L);
        Assertions.assertThat(questRepository.containsID(1L))
                .isFalse();
    }
}
