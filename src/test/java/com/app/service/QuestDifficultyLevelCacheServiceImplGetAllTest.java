package com.app.service;
import com.app.difficulty_level.DifficultyLevel;
import com.app.game.service.impl.QuestDifficultyLevelCacheServiceImpl;
import com.app.repository.QuestRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Map;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(MockitoExtension.class)
public class QuestDifficultyLevelCacheServiceImplGetAllTest {

    @Mock
    private QuestRepository<Long> questRepository;

    @InjectMocks
    private QuestDifficultyLevelCacheServiceImpl questDifficultyLevelCacheService;

    @RepeatedTest(5)
    @DisplayName("When start")
    public void test1(){


        Mockito.when(questRepository.getQuests())
                .thenReturn(List.of(
                        QUEST1,
                        QUEST2,
                        QUEST3,
                        QUEST4
                ));

        questDifficultyLevelCacheService.postConstruct();

        Assertions.assertThat(questDifficultyLevelCacheService.getAll())
                .isEqualTo(Map.of(
                        DifficultyLevel.A, List.of(QUEST1, QUEST2),
                        DifficultyLevel.B, List.of(QUEST3, QUEST4)));
    }
}
