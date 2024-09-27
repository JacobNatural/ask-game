package com.app.service;

import com.app.difficulty_level.DifficultyLevel;
import com.app.game.service.QuestCacheService;
import com.app.game.service.impl.QuestServiceImpl;
import com.app.quest.Quest;
import com.app.repository.QuestRepository;
import com.app.txt.save.FileWriter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.app.data_provider.DataProvider.QUEST1;

@ExtendWith(MockitoExtension.class)
public class QuestServiceImplAddQuestTest {

    @Mock
    private QuestRepository<Long> questRepository;

    @Mock
    private QuestCacheService<DifficultyLevel> questCacheDifficultyService;

    @Mock
    private FileWriter<Quest> questFileWriter;

    @InjectMocks
    private QuestServiceImpl questService;

    @Test
    @DisplayName("When the quest is null")
    public void test1() {

        Assertions.assertThatThrownBy(() ->
                        questService.addQuest(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quest cannot be null");
    }

    @Test
    @DisplayName("When the quest contain object")
    public void test2() {

        Mockito.when(questRepository.containsValue(QUEST1))
                .thenReturn(true);

        Assertions.assertThatThrownBy(() ->
                        questService.addQuest(QUEST1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quest already exists");

        Mockito.verify(questRepository, Mockito.times(1)).containsValue(QUEST1);
    }

    @Test
    @DisplayName("When add quest to repository")
    public void test3() {

        Assertions.assertThatNoException().isThrownBy(() ->
                questService.addQuest(QUEST1));

        InOrder inOrder = Mockito.inOrder(questRepository);

        inOrder.verify(questRepository, Mockito.times(1))
                .containsValue(ArgumentMatchers.any(Quest.class));

        Mockito.verify(questRepository, Mockito.times(1))
                .addQuest(ArgumentMatchers.any(Quest.class));
    }


}
