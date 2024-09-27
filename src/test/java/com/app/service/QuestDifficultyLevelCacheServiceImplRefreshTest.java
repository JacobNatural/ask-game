package com.app.service;

import com.app.game.service.impl.QuestDifficultyLevelCacheServiceImpl;
import com.app.repository.QuestRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuestDifficultyLevelCacheServiceImplRefreshTest {
    @Mock
    private QuestRepository<Long> questRepository;

    @InjectMocks
    private QuestDifficultyLevelCacheServiceImpl questDifficultyLevelCacheService;

    @Test
    @DisplayName("When the mapper is null")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                questDifficultyLevelCacheService.refresh(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Mapper cannot be null");
    }
}
