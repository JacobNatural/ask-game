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
public class QuestRepositoryImplTest {
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

    static QuestRepositoryImpl questRepository;

    @BeforeAll
    public static void setUp() {
        questRepository = new QuestRepositoryImpl(jdbiExtension.getJdbi());

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
    @DisplayName("When saving the quest")
    public void test1() {

        Assertions.assertThat(questRepository.save(QUEST_4))
                .isEqualTo(QUEST_4);
    }

    @Test
    @DisplayName("When saving the quests")
    public void test2() {

        Assertions.assertThat(questRepository.saveAll(List.of(QUEST_4, QUEST_5)))
                .contains(QUEST_4,QUEST_5);
    }

    @Test
    @DisplayName("When delete the quest")
    public void test3() {

        Assertions.assertThat(questRepository.delete(1L))
                .isEqualTo(QUEST_1);
    }


    @Test
    @DisplayName("When delete the quest and the id not found")
    public void test4() {

        Assertions.assertThatThrownBy(() ->
                        questRepository.delete(22L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Id not found");
    }

    @Test
    @DisplayName("When find by ID the quest")
    public void test5() {

        Assertions.assertThat(questRepository.findById(1L).get())
                .isEqualTo(QUEST_1);
    }


    @Test
    @DisplayName("When find last the quest")
    public void test6() {

        Assertions.assertThat(questRepository.findLast(1))
                .isEqualTo(List.of(QUEST_3));
    }

    @Test
    @DisplayName("When find all by IDS the quests")
    public void test7() {

        Assertions.assertThat(questRepository.findAllById(List.of(1L, 2L)))
                .contains(QUEST_1,QUEST_2);
    }

    @Test
    @DisplayName("When find all the quests")
    public void test8() {

        Assertions.assertThat(questRepository.findAll())
                .contains(QUEST_1, QUEST_2, QUEST_3);
    }
}
