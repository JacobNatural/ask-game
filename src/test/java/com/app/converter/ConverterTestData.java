package com.app.converter;

import com.app.model.difficulty_level.DifficultyLevel;
import com.app.model.player.PlayerData;
import com.app.model.quest.QuestData;
import com.app.repository.model.PlayerWithStatistic;
import com.app.repository.model.Quest;
import com.app.repository.model.QuestWithAnswer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ConverterTestData {

    PlayerWithStatistic PLAYER_WITH_STATISTIC_1 = new PlayerWithStatistic(
            1L,
            "Jakub",
            LocalDateTime.parse("2024-02-03T22:22:22"),
            65
    );

    PlayerWithStatistic PLAYER_WITH_STATISTIC_2 = new PlayerWithStatistic(
            1L,
            "Jakub",
            LocalDateTime.parse("2024-02-03T12:02:22"),
            75
    );

    PlayerWithStatistic PLAYER_WITH_STATISTIC_3 = new PlayerWithStatistic(
            2L,
            "Darek",
            LocalDateTime.parse("2024-01-02T20:22:22"),
            90
    );

    PlayerData PLAYER_DATA_1 = new PlayerData(
            1L,
            "Jakub",
            Map.of(
                    LocalDateTime.parse("2024-02-03T22:22:22"),
                    65,
                    LocalDateTime.parse("2024-02-03T12:02:22"),
                    75
            )
    );

    PlayerData PLAYER_DATA_2 = new PlayerData(
            2L,
            "Darek",
            Map.of(
                    LocalDateTime.parse("2024-01-02T20:22:22"),
                    90
            )
    );

    QuestWithAnswer QUEST_WITH_ANSWER_1 = new QuestWithAnswer(
            1L,
            DifficultyLevel.B,
            "history",
            "Car manufactured in Poland?",
            "Mercedes",
            false
    );

    QuestWithAnswer QUEST_WITH_ANSWER_2 = new QuestWithAnswer(
            1L,
            DifficultyLevel.B,
            "history",
            "Car manufactured in Poland?",
            "Fiat",
            true
    );

    QuestWithAnswer QUEST_WITH_ANSWER_3 = new QuestWithAnswer(
            1L,
            DifficultyLevel.B,
            "history",
            "Car manufactured in Poland?",
            "Ford",
            false
    );

    QuestWithAnswer QUEST_WITH_ANSWER_4 = new QuestWithAnswer(
            2L,
            DifficultyLevel.A,
            "biology",
            "Popular color of a squirrel?",
            "Red",
            true
    );

    QuestWithAnswer QUEST_WITH_ANSWER_5 = new QuestWithAnswer(
            2L,
            DifficultyLevel.A,
            "biology",
            "Popular color of a squirrel?",
            "White",
            false
    );

    QuestWithAnswer QUEST_WITH_ANSWER_6 = new QuestWithAnswer(
            2L,
            DifficultyLevel.A,
            "biology",
            "Popular color of a squirrel?",
            "Black",
            false
    );

    QuestWithAnswer QUEST_WITH_ANSWER_7 = new QuestWithAnswer(
            3L,
            DifficultyLevel.B,
            "history",
            "Who was the first Roman emperor?",
            "Julius Caesar",
            false
    );

    QuestWithAnswer QUEST_WITH_ANSWER_8 = new QuestWithAnswer(
            3L,
            DifficultyLevel.B,
            "history",
            "Who was the first Roman emperor?",
            "Nero",
            false
    );
    QuestWithAnswer QUEST_WITH_ANSWER_9 = new QuestWithAnswer(
            3L,
            DifficultyLevel.B,
            "history",
            "Who was the first Roman emperor?",
            "Augustus",
            true
    );


    List<QuestWithAnswer> QUEST_WITH_ANSWERS_1 = List.of(
            QUEST_WITH_ANSWER_1,
            QUEST_WITH_ANSWER_2,
            QUEST_WITH_ANSWER_3,
            QUEST_WITH_ANSWER_4,
            QUEST_WITH_ANSWER_5,
            QUEST_WITH_ANSWER_6
    );

    List<QuestWithAnswer> QUEST_WITH_ANSWERS_2 = List.of(
            QUEST_WITH_ANSWER_1,
            QUEST_WITH_ANSWER_2,
            QUEST_WITH_ANSWER_3,
            QUEST_WITH_ANSWER_4,
            QUEST_WITH_ANSWER_5,
            QUEST_WITH_ANSWER_6,
            QUEST_WITH_ANSWER_7,
            QUEST_WITH_ANSWER_8,
            QUEST_WITH_ANSWER_9
    );

    QuestData QUEST_DATA_1 = new QuestData(
            1L,
            DifficultyLevel.B,
            "history",
            "Car manufactured in Poland?",
            Map.of(
                    "Mercedes", false,
                    "Fiat", true,
                    "Ford", false
            )
    );

    QuestData QUEST_DATA_2 = new QuestData(
            2L,
            DifficultyLevel.A,
            "biology",
            "Popular color of a squirrel?",
            Map.of(
                    "Red", true,
                    "White", false,
                    "Black", false
            )
    );

    QuestData QUEST_DATA_3 = new QuestData(
            3L,
            DifficultyLevel.B,
            "history",
            "Who was the first Roman emperor?",
            Map.of(
                    "Julius Caesar", false,
                    "Nero", false,
                    "Augustus", true
            )
    );

    QuestData QUEST_DATA_4 = new QuestData(
            1L,
            DifficultyLevel.B,
            "history",
            "Car manufactured in Poland?",
            new HashMap<>()
    );

    Quest QUEST_1 = new Quest(1L,
            DifficultyLevel.B,
            "history",
            "Car manufactured in Poland?");


}
