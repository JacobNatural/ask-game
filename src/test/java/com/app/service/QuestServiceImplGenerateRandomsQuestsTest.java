package com.app.service;

import com.app.data_provider.DataProvider;
import com.app.difficulty_level.DifficultyLevel;
import com.app.game.service.QuestCacheService;
import com.app.game.service.impl.QuestServiceImpl;
import com.app.quest.Quest;
import com.app.quest.QuestMapper;
import com.app.repository.QuestRepository;
import com.app.txt.save.FileWriter;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith({MockitoExtension.class})
public class QuestServiceImplGenerateRandomsQuestsTest {

    @Mock
    private QuestRepository<Long> questRepository;

    @Mock
    private QuestCacheService<DifficultyLevel> questCacheDifficultyService;

    @Mock
    private FileWriter<Quest> questFileWriter;

    @InjectMocks
    private QuestServiceImpl questService;

    @RepeatedTest(3)
    @DisplayName("When generating the correct set of questions")
    public void test1() {

        Mockito.when(questCacheDifficultyService.getAll())
                .thenReturn(DataProvider.QUEST_FOR_ANSWERS());

        Condition<Quest> difficultyLevelConditionA = new Condition<>(
                quest -> QuestMapper.toDifficulty.apply(quest).equals(DifficultyLevel.A),
                "Quest has difficulty level A"
        );

        Condition<Quest> difficultyLevelConditionB = new Condition<>(
                quest -> QuestMapper.toDifficulty.apply(quest).equals(DifficultyLevel.B),
                "Quest has difficulty level B"
        );

        Condition<Quest> difficultyLevelConditionC = new Condition<>(
                quest -> QuestMapper.toDifficulty.apply(quest).equals(DifficultyLevel.C),
                "Quest has difficulty level C"
        );

        Assertions.assertThat(questService.generateRandomsQuests())
                .hasSize(12)
                .areExactly(4, difficultyLevelConditionA)
                .areExactly(4, difficultyLevelConditionB)
                .areExactly(4, difficultyLevelConditionC);

        Mockito.verify(questCacheDifficultyService, Mockito.times(1))
                .getAll();
    }

    @Test
    @DisplayName("When is not enough quest to generate")
    public void test2() {

        Mockito.when(questCacheDifficultyService.getAll())
                .thenReturn(new HashMap<>(Map.of(
                        DifficultyLevel.A, List.of(QUEST1, QUEST2),
                        DifficultyLevel.B, List.of(QUEST3, QUEST4))));


        Assertions.assertThatThrownBy(() ->
                        questService.generateRandomsQuests())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Not enough quests");

        Mockito.verify(questCacheDifficultyService, Mockito.times(1))
                .getAll();
    }
}
