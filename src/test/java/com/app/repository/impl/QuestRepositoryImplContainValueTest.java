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
public class QuestRepositoryImplContainValueTest {
    private final QuestRepositoryImpl questRepository;
    @Test
    @DisplayName("When the player repository contains a value")
    public void test1(){

        Assertions.assertThat(
                        questRepository.containsValue(QUEST2))
                .isTrue();
    }

    @Test
    @DisplayName("When the player repository does not contain a value")
    public void test2(){

        Assertions.assertThat(
                        questRepository.containsValue(QUEST4))
                .isFalse();
    }
}
