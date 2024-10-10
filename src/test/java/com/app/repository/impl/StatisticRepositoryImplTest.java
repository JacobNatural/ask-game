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
import java.util.List;

import static com.app.repository.RepositoryTestData.*;

@Testcontainers(disabledWithoutDocker = true)
public class StatisticRepositoryImplTest{
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
    static StatisticRepositoryImpl statisticRepository;

    @BeforeAll
    public static void setUp() {
        statisticRepository = new StatisticRepositoryImpl(jdbiExtension.getJdbi());
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
    @DisplayName("When saving the statistic")
    public void test1() {

        Assertions.assertThat(statisticRepository.save(STATISTIC_7))
                .isEqualTo(STATISTIC_7);
    }

    @Test
    @DisplayName("When saving the statistics")
    public void test2() {

        Assertions.assertThat(statisticRepository.saveAll(List.of(STATISTIC_7, STATISTIC_8)))
                .contains(STATISTIC_7, STATISTIC_8);
    }

    @Test
    @DisplayName("When delete the statistic")
    public void test3() {

        Assertions.assertThat(statisticRepository.delete(1L))
                .isEqualTo(STATISTIC_1);
    }


    @Test
    @DisplayName("When delete the statistic and the id not found")
    public void test4() {

        Assertions.assertThatThrownBy(() ->
                        statisticRepository.delete(22L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Id not found");
    }

    @Test
    @DisplayName("When find by ID the statistic")
    public void test5() {

        Assertions.assertThat(statisticRepository.findById(1L).get())
                .isEqualTo(STATISTIC_1);
    }


    @Test
    @DisplayName("When find last the statistic")
    public void test6() {

        Assertions.assertThat(statisticRepository.findLast(1))
                .isEqualTo(List.of(STATISTIC_6));
    }

    @Test
    @DisplayName("When find all by IDS the statistics")
    public void test7() {

        Assertions.assertThat(statisticRepository.findAllById(List.of(1L, 2L)))
                .contains(STATISTIC_1, STATISTIC_2);
    }


    @Test
    @DisplayName("When find all the statistics")
    public void test8() {

        Assertions.assertThat(statisticRepository.findAll())
                .contains(STATISTIC_1, STATISTIC_2, STATISTIC_3, STATISTIC_4, STATISTIC_5, STATISTIC_6);
    }

    @Test
    @DisplayName("When find by quest ID the statistics")
    public void test9() {

        Assertions.assertThat(statisticRepository.findByPlayerId(1L))
                .contains(STATISTIC_1, STATISTIC_2);
    }

    @Test
    @DisplayName("When delete by quest ID the statistics")
    public void test10() {

        Assertions.assertThat(statisticRepository.deleteByPlayerId(1L))
                .contains(STATISTIC_1, STATISTIC_2);
    }
}
