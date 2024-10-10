package com.app.service.impl;

import com.app.converter.FromQuestWithAnswerToQuestData;
import com.app.model.difficulty_level.DifficultyLevel;
import com.app.repository.QuestWithAnswerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;
import java.util.Map;

import static com.app.service.ServiceTestData.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings()

public class QuestDifficultyLevelCacheServiceImplTest {

    @Mock
    private QuestWithAnswerRepository questWithAnswerRepository;

    @Mock
    private FromQuestWithAnswerToQuestData fromQuestWithAnswerToQuestData;

    @InjectMocks
    private QuestDifficultyLevelCacheServiceImpl questDifficultyLevelCacheService;


    @Test
    @DisplayName("When start the refresh with mapper null")
    public void test1() {

        Assertions.assertThatThrownBy(() ->
                        questDifficultyLevelCacheService.refresh(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Mapper cannot be null");
    }

    @Test
    @DisplayName("When the refresh starts and does not throw an exception")
    public void test2() {

        Mockito.when(questWithAnswerRepository.findQuestsWithAnswers())
                .thenReturn(List.of());

        Mockito.when(fromQuestWithAnswerToQuestData.toGroupBy(
                        ArgumentMatchers.anyList(), ArgumentMatchers.any()))
                .thenReturn(Map.of(
                        DifficultyLevel.A, List.of(QUEST_DATA_1, QUEST_DATA_2),
                        DifficultyLevel.B, List.of(QUEST_DATA_3)
                ));

        Assertions.assertThatNoException().isThrownBy(() ->
                questDifficultyLevelCacheService.postConstruct());

        var inOrder = Mockito.inOrder(questWithAnswerRepository, fromQuestWithAnswerToQuestData);

        inOrder.verify(questWithAnswerRepository, Mockito.times(1))
                .findQuestsWithAnswers();

        inOrder.verify(fromQuestWithAnswerToQuestData, Mockito.times(1))
                .toGroupBy(ArgumentMatchers.anyList(), ArgumentMatchers.any());


    }

    @Test
    @DisplayName("When the grouped quest is null")
    public void test3() {

        Assertions.assertThat(questDifficultyLevelCacheService.getAll())
                .isNull();
    }

    @Test
    @DisplayName("When the grouped quest is refreshed and returns correct data")
    public void test4() {

        Mockito.when(questWithAnswerRepository.findQuestsWithAnswers())
                .thenReturn(List.of());

        Mockito.when(fromQuestWithAnswerToQuestData.toGroupBy(
                        ArgumentMatchers.anyList(), ArgumentMatchers.any()))
                .thenReturn(Map.of(
                        DifficultyLevel.A, List.of(QUEST_DATA_1, QUEST_DATA_2),
                        DifficultyLevel.B, List.of(QUEST_DATA_3)
                ));

        questDifficultyLevelCacheService.postConstruct();

        Assertions.assertThat(questDifficultyLevelCacheService.getAll())
                .hasSize(2)
                .containsExactlyInAnyOrderEntriesOf(Map.ofEntries(
                        Map.entry(DifficultyLevel.A, List.of(QUEST_DATA_1, QUEST_DATA_2)),
                        Map.entry(DifficultyLevel.B, List.of(QUEST_DATA_3))
                ));

        var inOrder = Mockito.inOrder(questWithAnswerRepository, fromQuestWithAnswerToQuestData);

        inOrder.verify(questWithAnswerRepository, Mockito.times(1))
                .findQuestsWithAnswers();

        inOrder.verify(fromQuestWithAnswerToQuestData, Mockito.times(1))
                .toGroupBy(ArgumentMatchers.anyList(), ArgumentMatchers.any());
    }
}
