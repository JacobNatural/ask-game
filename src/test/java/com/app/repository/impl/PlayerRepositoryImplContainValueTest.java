package com.app.repository.impl;

import com.app.extension.repository.PlayerRepositoryExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.app.data_provider.DataProvider.PLAYER1;
import static com.app.data_provider.DataProvider.PLAYER2;

@RequiredArgsConstructor
@ExtendWith(PlayerRepositoryExtension.class)
public class PlayerRepositoryImplContainValueTest {
    private final PlayerRepositoryImpl playerRepository;

    @Test
    @DisplayName("When the player repository contains a value")
    public void test1(){

        Assertions.assertThat(
                playerRepository.containsValue(PLAYER2))
                .isTrue();
    }

    @Test
    @DisplayName("When the player repository does not contain a value")
    public void test2(){

        Assertions.assertThat(
                        playerRepository.containsValue(PLAYER1))
                .isFalse();
    }
}
