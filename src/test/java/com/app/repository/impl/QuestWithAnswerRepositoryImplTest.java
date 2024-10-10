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
public class QuestWithAnswerRepositoryImplTest {
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

    static QuestWithAnswerRepositoryImpl questWithAnswerRepository;

    @BeforeAll
    public static void setUp() {
        questWithAnswerRepository = new QuestWithAnswerRepositoryImpl(jdbiExtension.getJdbi());

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
    @DisplayName("When you get the quest with answers")
    public void test1() {
        Assertions.assertThat(questWithAnswerRepository.findQuestWithAnswer(1L))
                .contains(QUEST_WITH_ANSWER_1, QUEST_WITH_ANSWER_2, QUEST_WITH_ANSWER_3);
    }

    @Test
    @DisplayName("When you get the quests with answers")
    public void test2() {
        Assertions.assertThat(questWithAnswerRepository.findQuestsWithAnswers())
                .contains(
                        QUEST_WITH_ANSWER_1, QUEST_WITH_ANSWER_2, QUEST_WITH_ANSWER_3,
                        QUEST_WITH_ANSWER_4, QUEST_WITH_ANSWER_5, QUEST_WITH_ANSWER_6,
                        QUEST_WITH_ANSWER_7, QUEST_WITH_ANSWER_8, QUEST_WITH_ANSWER_9);
    }

    @Test
    @DisplayName("When you get the  best player with statistics")
    public void test3() {
        Assertions.assertThat(questWithAnswerRepository.findQuestsWithAnswersByIds(List.of(1L,2L)))
                .contains(QUEST_WITH_ANSWER_1, QUEST_WITH_ANSWER_2, QUEST_WITH_ANSWER_3,
                        QUEST_WITH_ANSWER_4, QUEST_WITH_ANSWER_5, QUEST_WITH_ANSWER_6);
    }


}
