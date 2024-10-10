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
import java.util.Map;

import static com.app.repository.RepositoryTestData.*;

@Testcontainers(disabledWithoutDocker = true)
public class PlayerRepositoryImplTest {
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

    static PlayerRepositoryImpl playerRepository;

    @BeforeAll
    public static void setUp() {
        playerRepository = new PlayerRepositoryImpl(jdbiExtension.getJdbi());

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
    @DisplayName("When saving the player")
    public void test1() {

        Assertions.assertThat(playerRepository.save(PLAYER_4))
                .isEqualTo(PLAYER_4);
    }

    @Test
    @DisplayName("When saving the players")
    public void test2() {

        Assertions.assertThat(playerRepository.saveAll(List.of(PLAYER_4, PLAYER_5)))
                .contains(PLAYER_4, PLAYER_5);
    }

    @Test
    @DisplayName("When delete the player")
    public void test3() {

        Assertions.assertThat(playerRepository.delete(1L))
                .isEqualTo(PLAYER_1);
    }


    @Test
    @DisplayName("When delete the player and the id not found")
    public void test4() {


        Assertions.assertThatThrownBy(() ->
                        playerRepository.delete(22L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Id not found");
    }

    @Test
    @DisplayName("When find by ID the player")
    public void test5() {

        Assertions.assertThat(playerRepository.findById(1L).get())
                .isEqualTo(PLAYER_1);
    }


    @Test
    @DisplayName("When find last the player")
    public void test6() {

        Assertions.assertThat(playerRepository.findLast(1))
                .isEqualTo(List.of(PLAYER_3));
    }

    @Test
    @DisplayName("When find all by IDS the players")
    public void test7() {

        Assertions.assertThat(playerRepository.findAllById(List.of(1L, 2L)))
                .contains(PLAYER_1, PLAYER_2);
    }


    @Test
    @DisplayName("When find all the players")
    public void test8() {

        Assertions.assertThat(playerRepository.findAll())
                .contains(PLAYER_1, PLAYER_2, PLAYER_3);
    }

    @Test
    @DisplayName("When get ranking of players")
    public void test9(){
        var ranking = playerRepository.getPlayersRanking();
        System.out.println(ranking);
        Assertions.assertThat(ranking)
                .hasSize(2)
                .containsExactlyInAnyOrderEntriesOf(Map.ofEntries(
                        Map.entry(140,"Jony"),
                        Map.entry(120, "Jakub, Darek")
                        )
                );
    }
}
