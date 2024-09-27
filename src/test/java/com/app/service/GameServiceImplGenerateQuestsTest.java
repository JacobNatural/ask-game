package com.app.service;

import com.app.difficulty_level.DifficultyLevel;
import com.app.game.service.PlayerService;
import com.app.game.service.QuestService;
import com.app.game.service.impl.GameServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceImplGenerateQuestsTest {

    @Mock
    private QuestService<DifficultyLevel> questService;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private GameServiceImpl gameServiceImpl;

    @Test
    @DisplayName("When creating QuestDTO properly")
    public void test1(){

        Mockito.when(questService.generateRandomsQuests())
                        .thenReturn(List.of(
                                QUEST1,QUEST2,
                                QUEST3, QUEST4));

        var generateQuests = gameServiceImpl.generateQuests();
        var questsDTO = List.of(
                QUEST_DTO_1,
                QUEST_DTO_2,
                QUEST_DTO_3,
                QUEST_DTO_4);

        Assertions.assertThat(generateQuests)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("answers")
                .hasSameElementsAs(    questsDTO);

        for(int i = 0; i< generateQuests.size(); i++){
            var actualQuest = generateQuests.get(i);
            var expectedQuest = questsDTO.get(i);

            Assertions.assertThat(actualQuest.answers())
                    .containsExactlyInAnyOrderEntriesOf(expectedQuest.answers());
        }

        Mockito.verify(questService, Mockito.times(1))
                .generateRandomsQuests();
    }
}
