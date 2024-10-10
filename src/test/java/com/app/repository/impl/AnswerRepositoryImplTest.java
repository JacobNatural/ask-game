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
public class AnswerRepositoryImplTest {
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

    static AnswerRepositoryImpl answerRepository;

    @BeforeAll
    public static void setUp() {
        answerRepository = new AnswerRepositoryImpl(jdbiExtension.getJdbi());

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
    @DisplayName("When saving the answer")
    public void test1() {

        Assertions.assertThat(answerRepository.save(ANSWER_10))
                .isEqualTo(ANSWER_10);
    }

    @Test
    @DisplayName("When saving the answers")
    public void test2() {

        Assertions.assertThat(answerRepository.saveAll(List.of(ANSWER_10, ANSWER_11)))
                .contains(ANSWER_10, ANSWER_11);
    }

    @Test
    @DisplayName("When delete the answer")
    public void test3() {

        Assertions.assertThat(answerRepository.delete(1L))
                .isEqualTo(ANSWER_1);
    }


    @Test
    @DisplayName("When delete the answer and the id not found")
    public void test4() {


        Assertions.assertThatThrownBy(() ->
                        answerRepository.delete(22L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Id not found");
    }

    @Test
    @DisplayName("When find by ID the answer")
    public void test5() {

        Assertions.assertThat(answerRepository.findById(1L).get())
                .isEqualTo(ANSWER_1);
    }


    @Test
    @DisplayName("When find last the answer")
    public void test6() {

        Assertions.assertThat(answerRepository.findLast(1))
                .isEqualTo(List.of(ANSWER_9));
    }

    @Test
    @DisplayName("When find all by IDS the answers")
    public void test7() {

        Assertions.assertThat(answerRepository.findAllById(List.of(1L, 2L)))
                .contains(ANSWER_1, ANSWER_2);
    }

    @Test
    @DisplayName("When find all the answers")
    public void test8() {

        Assertions.assertThat(answerRepository.findAll())
                .contains(ANSWER_1, ANSWER_2, ANSWER_3, ANSWER_4, ANSWER_5, ANSWER_6, ANSWER_7, ANSWER_8, ANSWER_9);
    }

    @Test
    @DisplayName("When find by quest ID the answers")
    public void test9() {

        Assertions.assertThat(answerRepository.findByQuestId(1L))
                .contains(ANSWER_1, ANSWER_2, ANSWER_3);
    }

    @Test
    @DisplayName("When delete by quest ID the answers")
    public void test10() {

        Assertions.assertThat(answerRepository.deleteByQuestId(1L))
                .contains(ANSWER_1, ANSWER_2, ANSWER_3);
    }
}
