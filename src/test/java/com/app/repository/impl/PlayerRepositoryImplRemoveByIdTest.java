package com.app.repository.impl;

import com.app.extension.repository.PlayerRepositoryExtension;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@RequiredArgsConstructor
@ExtendWith(PlayerRepositoryExtension.class)
public class PlayerRepositoryImplRemoveByIdTest {
    private final PlayerRepositoryImpl playerRepository;

    @Test
    @DisplayName("When removing an ID from the player repository")
    @SneakyThrows
    public void test1(){
        playerRepository.removeByID(1L);
        Assertions.assertThat(playerRepository.containsID(1L))
                .isFalse();
    }
}
