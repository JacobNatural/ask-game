package com.app.repository.impl;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.jdbi.v3.testing.junit5.JdbiExtension;
import org.jdbi.v3.testing.junit5.tc.JdbiTestcontainersExtension;
import org.jdbi.v3.testing.junit5.tc.TestcontainersDatabaseInformation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static com.app.repository.RepositoryTestData.*;

@Testcontainers(disabledWithoutDocker = true)
public class PlayersWithStatisticsRepositoryImplTest {
    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:latest")
            .withUsername("user")
            .withPassword("password")
            .withDatabaseName("test_db")
            .withInitScript("scripts/test_init.sql");

    private static final TestcontainersDatabaseInformation MYSQL = TestcontainersDatabaseInformation.of(
            "user", "test_db", null,
            (catalogName, schemaName) -> String.format("create database if not exists %s", catalogName)
    );

    @RegisterExtension
    static JdbiExtension jdbiExtension = JdbiTestcontainersExtension
            .instance(MYSQL, MY_SQL_CONTAINER);

    static PlayersWithStatisticsRepositoryImpl playersWithStatisticsRepository;

    @BeforeAll
    public static void setUp() {
        playersWithStatisticsRepository = new PlayersWithStatisticsRepositoryImpl(jdbiExtension.getJdbi());

    }

    @BeforeEach
    public void restartDatabase() {
        jdbiExtension.getJdbi().useHandle(handle -> {
            handle.execute("DELETE FROM answers");
            handle.execute("DELETE FROM quests");
            handle.execute("DELETE FROM statistics");
            handle.execute("DELETE FROM players");
            handle.execute("ALTER TABLE answers AUTO_INCREMENT = 1");
            handle.execute("ALTER TABLE quests AUTO_INCREMENT = 1");
            handle.execute("ALTER TABLE players AUTO_INCREMENT = 1");
            handle.execute("ALTER TABLE statistics AUTO_INCREMENT = 1");
        });

        var script = loadSqlFormResource("scripts/test_init.sql");

        jdbiExtension.getJdbi().useHandle(handle ->
                handle.createScript(script).execute());
    }

    @SneakyThrows
    private String loadSqlFormResource(String resourcePath) {

        var resourceURL = getClass().getClassLoader().getResource(resourcePath).toURI();

        return Files.readString(Path.of(resourceURL));
    }

    @Test
    @DisplayName("When you get the player with statistics")
    public void test1(){
        Assertions.assertThat(playersWithStatisticsRepository.findPlayerWithStatistic(1L))
                .contains(PLAYER_WITH_STATISTIC_1, PLAYER_WITH_STATISTIC_2);
    }

    @Test
    @DisplayName("When you get the players with statistics")
    public void test2(){
        Assertions.assertThat(playersWithStatisticsRepository.findPlayersWithStatistics())
                .contains(
                        PLAYER_WITH_STATISTIC_1, PLAYER_WITH_STATISTIC_2,
                        PLAYER_WITH_STATISTIC_3, PLAYER_WITH_STATISTIC_4,
                        PLAYER_WITH_STATISTIC_5, PLAYER_WITH_STATISTIC_6);
    }

    @Test
    @DisplayName("When you get the  best player with statistics")
    public void test3(){
        Assertions.assertThat(playersWithStatisticsRepository.getTheBestPlayer())
                .contains(PLAYER_WITH_STATISTIC_1);
    }

    @Test
    @DisplayName("When you get the best player with statistics in a given period")
    public void test4(){
        Assertions.assertThat(playersWithStatisticsRepository.getTheBestPlayerInPeriod(
                        LocalDateTime.parse("2024-08-01T13:13:33"), LocalDateTime.parse("2024-08-22T14:30:23"))
                ).contains(PLAYER_WITH_STATISTIC_4, PLAYER_WITH_STATISTIC_6);
    }
}
