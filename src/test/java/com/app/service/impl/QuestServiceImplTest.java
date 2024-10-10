package com.app.service.impl;

import com.app.converter.FromQuestWithAnswerToQuestData;
import com.app.model.difficulty_level.DifficultyLevel;
import com.app.repository.AnswerRepository;
import com.app.repository.QuestRepository;
import com.app.repository.QuestWithAnswerRepository;
import com.app.repository.model.Quest;
import com.app.service.QuestCacheService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static com.app.service.ServiceTestData.*;


@ExtendWith(MockitoExtension.class)
public class QuestServiceImplTest {

    @Mock
    private QuestRepository questRepository;

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private QuestWithAnswerRepository questWithAnswerRepository;

    @Mock
    private QuestCacheService<DifficultyLevel> questCacheService;

    @Mock
    private FromQuestWithAnswerToQuestData fromQuestWithAnswerToQuestData;

    @InjectMocks
    private QuestServiceImpl questService;

    @Test
    @DisplayName("When saving the QuestData and QuestData is null")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                questService.addQuest(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quest cannot be null");
    }

    @Test
    @DisplayName("When saving the QuestData")
    public void test2(){

        Mockito.when(questRepository.save(ArgumentMatchers.any(Quest.class)))
                .thenReturn(QUEST_1);

        Mockito.when(answerRepository.saveAll(ArgumentMatchers.anyList()))
                        .thenReturn(ANSWERS_1);

        Assertions.assertThat(questService.addQuest(QUEST_DATA_1))
                .isEqualTo(QUEST_DATA_1);

        var inOrder = Mockito.inOrder(questRepository, answerRepository);

        inOrder.verify(questRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Quest.class));

        inOrder.verify(answerRepository, Mockito.times(1))
                .saveAll(ArgumentMatchers.anyList());
    }

    @Test
    @DisplayName("When removing quest")
    public void test3(){

        Mockito.when(questRepository.delete(ArgumentMatchers.anyLong()))
                .thenReturn(QUEST_1);

        Assertions.assertThat(questService.removeQuest(1L))
                .isEqualTo(QUEST_DATA_EMPTY_MAP);

        Mockito.verify(questRepository, Mockito.times(1))
                .delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("When find quest by ID")
    public void test4(){

        Mockito.when(questWithAnswerRepository
                .findQuestWithAnswer(ArgumentMatchers.anyLong()))
                .thenReturn(QUEST_WITH_ANSWER_1);

        Mockito.when(fromQuestWithAnswerToQuestData
                .toList(ArgumentMatchers.anyList()))
                        .thenReturn(List.of(QUEST_DATA_1));

        Assertions.assertThat(questService.findQuest(1L))
                .isEqualTo(QUEST_DATA_1);
    }

    @Test
    @DisplayName("When generating random quests and there is not enough data")
    public void test5(){

        Mockito.when(questCacheService.getAll())
                .thenReturn(Map.of(
                        DifficultyLevel.A, List.of(QUEST_DATA_1, QUEST_DATA_2),
                        DifficultyLevel.B, List.of(QUEST_DATA_7, QUEST_DATA_8),
                        DifficultyLevel.C, List.of(QUEST_DATA_12, QUEST_DATA_13)
                ));

        Assertions.assertThatThrownBy(() ->
                        questService.generateRandomsQuests())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Not enough quests");

        Mockito.verify(questCacheService, Mockito.times(1))
                .getAll();
    }

    @RepeatedTest(6)
    @DisplayName("When generating random quests")
    public void test6(){

        Mockito.when(questCacheService.getAll())
                .thenReturn(QUEST_DATA_GROUPED_BY_DIFFICULTY_LEVEL);

        Assertions.assertThat(questService.generateRandomsQuests())
                .hasSize(12);

        Mockito.verify(questCacheService, Mockito.times(1))
                .getAll();
    }

    @Test
    @DisplayName("When calculate points and answerDto is null")
    public void test7(){
        Assertions.assertThatThrownBy(() ->
                questService.calculatePoints(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AnswersDto cannot be null");
    }

    @Test
    @DisplayName("When calculate points")
    public void test8(){

        Mockito.when(questWithAnswerRepository.findQuestsWithAnswersByIds(ArgumentMatchers.anyList()))
                        .thenReturn(List.of());

        Mockito.when(fromQuestWithAnswerToQuestData.toMap(
                ArgumentMatchers.anyList(), ArgumentMatchers.any()
        )).thenReturn(Map.of(
                1L,QUEST_DATA_1
                ,2L, QUEST_DATA_2,3L,
                QUEST_DATA_3,
                4L,QUEST_DATA_4,
                5L,QUEST_DATA_5,
                6L,QUEST_DATA_6));

        Assertions.assertThat(questService.calculatePoints(ANSWERS_DTO_1))
                .isEqualTo(30);
    }
}
