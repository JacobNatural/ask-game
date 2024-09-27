package com.app.repository.impl;

import com.app.extension.repository.PlayerRepositoryExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.app.data_provider.DataProvider.PLAYER4;

@ExtendWith(PlayerRepositoryExtension.class)
@RequiredArgsConstructor
public class PlayerRepositoryImplFindByIdTest {
    private final PlayerRepositoryImpl playerRepository;

    @Test
    @DisplayName("When find player by ID")
    public void test1() {
        Assertions.assertThat(playerRepository.findByID(1L))
                .isEqualTo(PLAYER4);
    }
}
