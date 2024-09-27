package com.app.service;

import com.app.data_provider.DataProvider;
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
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
public class QuestServiceImplSaveQuestToTxtTest {
    @Mock
    private QuestRepository<Long> questRepository;

    @Mock
    private QuestCacheService<DifficultyLevel> questCacheDifficultyService;

    @Mock
    private FileWriter<Quest> questFileWriter;

    @InjectMocks
    private QuestServiceImpl questService;

    @Test
    @DisplayName("When the function is null")
    public void test1(){

        Assertions.assertThatThrownBy(() ->
                questService.saveQuestToTxt(DataProvider.FILENAME_SAVE, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ToTxtFormat cannot be null");
    }

    @Test
    @DisplayName("When saving the quests to TXT")
    public void test2(){

       Mockito.when(questRepository.getQuests())
                       .thenReturn(List.of(QUEST1,QUEST2, QUEST3));

        Assertions.assertThatNoException().isThrownBy(
                () -> questService.saveQuestToTxt(DataProvider.FILENAME_SAVE,
                        Quest::toTxtFormat));

        Mockito.verify(questRepository, Mockito.times(1)).getQuests();

        Mockito.verify(questFileWriter, Mockito.times(1))
                .save(
                       ArgumentMatchers.anyString(),
                        ArgumentMatchers.eq(List.of(QUEST1, QUEST2, QUEST3)),
                        ArgumentMatchers.any());
    }
}
