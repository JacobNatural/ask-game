package com.app.repository.impl;

import com.app.extension.repository.PlayerRepositoryExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(PlayerRepositoryExtension.class)
@RequiredArgsConstructor
public class PlayerRepositoryImplAddObjectTest {
    private final PlayerRepositoryImpl playerRepositoryImpl;

    @Test
    @DisplayName("When adding a new player to the repository")
    public void test1() {
        playerRepositoryImpl.addPlayer(PLAYER1);

        Assertions.assertThat(playerRepositoryImpl.getAll().values())
                .contains(PLAYER1);
    }

    @Test
    @DisplayName("When adding a new player to the repository, and the player already exists than update statistic")
    public void test2() {
        playerRepositoryImpl.addPlayer(PLAYER5);

        Assertions.assertThat(playerRepositoryImpl.getAll().values())
                .contains(PLAYER6);
    }

    @Test
    @DisplayName("When adding a new player to the repository, and the player already exists than update statistic")
    public void test3() {
        playerRepositoryImpl.addPlayer(PLAYER5);

        Assertions.assertThat(playerRepositoryImpl.getAll().values())
                .contains(PLAYER6);
    }
}
