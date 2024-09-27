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
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)

public class QuestServiceImplCalculatePointsTest {

    @Mock
    private QuestRepository<Long> questRepository;

    @Mock
    private QuestCacheService<DifficultyLevel> questCacheDifficultyService;

    @Mock
    private FileWriter<Quest> questFileWriter;

    @InjectMocks
    private QuestServiceImpl questService;

    @Test
    @DisplayName("When answersDto is null")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                questService.calculatePoints(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("AnswersDto cannot be null");
    }


    @Test
    @DisplayName("When the answer is for a quest which does not exist")
    public void test2() {
        Assertions.assertThatThrownBy(() ->
                        questService.calculatePoints(ANSWERS_DTO_1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Quest not found");

        Mockito.verify(questRepository, Mockito.times(1))
                .containsID(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("When the answer in the quest not exist")
    public void test3() {

        Mockito.when(questRepository.findByID(1L))
                .thenReturn(QUEST1);

        Mockito.when(questRepository.findByID(2L))
                .thenReturn(QUEST2);

        Mockito.when(questRepository.findByID(3L))
                .thenReturn(QUEST4);

        Mockito.when(questRepository.containsID(ArgumentMatchers.anyLong()))
                .thenReturn(true);


        Assertions.assertThatThrownBy(() ->
                        questService.calculatePoints(ANSWERS_DTO_1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Answer not found");

        Mockito.verify(questRepository, Mockito.times(3))
                .containsID(ArgumentMatchers.anyLong());

        Mockito.verify(questRepository, Mockito.times(3))
                .findByID(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("When calculate points correctly")
    public void test4() {

        Mockito.when(questRepository.findByID(1L))
                .thenReturn(QUEST1);

        Mockito.when(questRepository.findByID(2L))
                .thenReturn(QUEST2);

        Mockito.when(questRepository.findByID(3L))
                .thenReturn(QUEST3);

        Mockito.when(questRepository.containsID(ArgumentMatchers.anyLong()))
                .thenReturn(true);


        Assertions.assertThat(
                        questService.calculatePoints(ANSWERS_DTO_1))
                .isEqualTo(25);
    }


}
