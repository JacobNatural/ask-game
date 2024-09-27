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

@ExtendWith(MockitoExtension.class)
public class QuestServiceRemoveQuestTest {
    @Mock
    private QuestRepository<Long> questRepository;

    @Mock
    private QuestCacheService<DifficultyLevel> questCacheDifficultyService;

    @Mock
    private FileWriter<Quest> questFileWriter;

    @InjectMocks
    private QuestServiceImpl questService;

    @Test
    @DisplayName("When the quest does not exist")
    public void test1(){
        Mockito.when(questRepository.containsID(ArgumentMatchers.anyLong()))
                .thenReturn(false);

        Assertions.assertThatThrownBy(() ->
                questService.removePlayer(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quest not found");

        Mockito.verify(questRepository, Mockito.times(1))
                .containsID(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("When the quest is removed by ID")
    public void test2(){
        Mockito.when(questRepository.containsID(ArgumentMatchers.anyLong()))
                .thenReturn(true);

        Assertions.assertThatNoException().isThrownBy(
                () -> questService.removePlayer(1L));

        InOrder inOrder = Mockito.inOrder(questRepository);

        inOrder.verify(questRepository, Mockito.times(1))
                .containsID(ArgumentMatchers.anyLong());

        inOrder.verify(questRepository, Mockito.times(1))
                .removeByID(ArgumentMatchers.anyLong());
    }
}
