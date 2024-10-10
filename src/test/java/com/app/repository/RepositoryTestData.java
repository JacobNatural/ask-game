package com.app.repository;

import com.app.model.difficulty_level.DifficultyLevel;
import com.app.repository.model.*;

import java.time.LocalDateTime;

public interface RepositoryTestData {

    Answer ANSWER_1 = new Answer(1L, 1L, "Wolfgang Amadeus Mozart", false);
    Answer ANSWER_2 = new Answer(2L, 1L, "Johann Strauss II", false);
    Answer ANSWER_3 = new Answer(3L, 1L, "Georges Bizet", true);
    Answer ANSWER_4 = new Answer(4L, 2L, "Warsaw", true);
    Answer ANSWER_5 = new Answer(5L, 2L, "Krakow", false);
    Answer ANSWER_6 = new Answer(6L, 2L, "Gdansk", false);
    Answer ANSWER_7 = new Answer(7L, 3L, "Ksiaz", false);
    Answer ANSWER_8 = new Answer(8L, 3L, "Czocha", false);
    Answer ANSWER_9 = new Answer(9L, 3L, "Malbork", true);
    Answer ANSWER_10 = new Answer(10L, 1L, "Warsaw", true);
    Answer ANSWER_11 = new Answer(11L, 1L, "Krakow", false);

    Quest QUEST_1 = new Quest(1L, DifficultyLevel.B, "music", "Who composed \"Carmen\"?");
    Quest QUEST_2 = new Quest(2L, DifficultyLevel.A, "culture",
            "In which city in Poland is the famous National Museum, one of the largest art museums in the country, located?");
    Quest QUEST_3 = new Quest(3L, DifficultyLevel.B, "geography",
            "In which city in Poland is the famous castle that is the largest in Europe by area?");
    Quest QUEST_4 = new Quest(4L, DifficultyLevel.A, "science",
            "Popular phone in the USA?");
    Quest QUEST_5 = new Quest(5L, DifficultyLevel.A, "biology",
            "Popular color of a squirrel?");

    Statistic STATISTIC_1 = new Statistic(1L, 1L, LocalDateTime.parse("2024-08-28T16:00:00"), 140);
    Statistic STATISTIC_2 = new Statistic(2L, 1L, LocalDateTime.parse("2024-08-28T14:30:00"), 60);
    Statistic STATISTIC_3 = new Statistic(3L, 2L, LocalDateTime.parse("2024-07-20T12:30:00"), 110);
    Statistic STATISTIC_4 = new Statistic(4L, 2L, LocalDateTime.parse("2024-08-04T13:30:00"), 120);
    Statistic STATISTIC_5 = new Statistic(5L, 2L, LocalDateTime.parse("2024-08-01T10:20:00"), 50);
    Statistic STATISTIC_6 = new Statistic(6L, 3L, LocalDateTime.parse("2024-08-10T18:35:00"), 120);
    Statistic STATISTIC_7 = new Statistic(7L, 3L, LocalDateTime.parse("2024-09-18T17:25:05"), 75);
    Statistic STATISTIC_8 = new Statistic(8L, 1L, LocalDateTime.parse("2024-09-20T16:23:13"), 115);

    Player PLAYER_1 = new Player(1L, "Jony");
    Player PLAYER_2 = new Player(2L, "Jakub");
    Player PLAYER_3 = new Player(3L, "Darek");
    Player PLAYER_4 = new Player(4L, "Krzysztof");
    Player PLAYER_5 = new Player(5L, "Mike");

    PlayerWithStatistic PLAYER_WITH_STATISTIC_1 =
            new PlayerWithStatistic(1L, "Jony", LocalDateTime.parse("2024-08-28T16:00:00"), 140);
    PlayerWithStatistic PLAYER_WITH_STATISTIC_2 =
            new PlayerWithStatistic(1L, "Jony", LocalDateTime.parse("2024-08-28T14:30:00"), 60);
    PlayerWithStatistic PLAYER_WITH_STATISTIC_3 =
            new PlayerWithStatistic(2L, "Jakub", LocalDateTime.parse("2024-07-20T12:30:00"), 110);
    PlayerWithStatistic PLAYER_WITH_STATISTIC_4 =
            new PlayerWithStatistic(2L, "Jakub", LocalDateTime.parse("2024-08-04T13:30:00"), 120);
    PlayerWithStatistic PLAYER_WITH_STATISTIC_5 =
            new PlayerWithStatistic(2L, "Jakub", LocalDateTime.parse("2024-08-01T10:20:00"), 50);
    PlayerWithStatistic PLAYER_WITH_STATISTIC_6 =
            new PlayerWithStatistic(3L, "Darek", LocalDateTime.parse("2024-08-10T18:35:00"), 120);

    QuestWithAnswer QUEST_WITH_ANSWER_1 =
            new QuestWithAnswer(1L, DifficultyLevel.B, "music",
                    "Who composed \"Carmen\"?", "Wolfgang Amadeus Mozart", false);
    QuestWithAnswer QUEST_WITH_ANSWER_2 =
            new QuestWithAnswer(1L, DifficultyLevel.B, "music",
                    "Who composed \"Carmen\"?", "Johann Strauss II", false);
    QuestWithAnswer QUEST_WITH_ANSWER_3 =
            new QuestWithAnswer(1L, DifficultyLevel.B, "music",
                    "Who composed \"Carmen\"?", "Georges Bizet", true);
    QuestWithAnswer QUEST_WITH_ANSWER_4 =
            new QuestWithAnswer(2L, DifficultyLevel.A, "culture",
                    "In which city in Poland is the famous National Museum, one of the largest art museums in the country, located?",
                    "Warsaw", true);
    QuestWithAnswer QUEST_WITH_ANSWER_5 =
            new QuestWithAnswer(2L, DifficultyLevel.A, "culture",
                    "In which city in Poland is the famous National Museum, one of the largest art museums in the country, located?",
                    "Krakow", false);
    QuestWithAnswer QUEST_WITH_ANSWER_6 =
            new QuestWithAnswer(2L, DifficultyLevel.A, "culture",
                    "In which city in Poland is the famous National Museum, one of the largest art museums in the country, located?",
                    "Gdansk", false);
    QuestWithAnswer QUEST_WITH_ANSWER_7 =
            new QuestWithAnswer(3L, DifficultyLevel.B, "geography",
                    "In which city in Poland is the famous castle that is the largest in Europe by area?",
                    "Ksiaz", false);
    QuestWithAnswer QUEST_WITH_ANSWER_8 =
            new QuestWithAnswer(3L, DifficultyLevel.B, "geography",
                    "In which city in Poland is the famous castle that is the largest in Europe by area?",
                    "Czocha", false);
    QuestWithAnswer QUEST_WITH_ANSWER_9 =
            new QuestWithAnswer(3L, DifficultyLevel.B, "geography",
                    "In which city in Poland is the famous castle that is the largest in Europe by area?",
                    "Malbork", true);


}
