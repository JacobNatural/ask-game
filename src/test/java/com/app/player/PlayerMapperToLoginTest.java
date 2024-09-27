package com.app.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.app.data_provider.DataProvider.PLAYER1;

public class PlayerMapperToLoginTest {
    @Test
    @DisplayName("When mapping a Player object to a login string, the correct question should be returned")
    public void test1(){
        Assertions.assertThat(PlayerMapper.toLogin.apply(PLAYER1))
                .isEqualTo("Darek");
    }
}
