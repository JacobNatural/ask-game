package com.app.service;

import com.app.dto.AnswersDto;
import com.app.dto.PlayerDto;
import com.app.dto.QuestDto;
import com.app.model.difficulty_level.DifficultyLevel;
import com.app.model.player.PlayerData;
import com.app.model.quest.QuestData;
import com.app.repository.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ServiceTestData {

    Player PLAYER_1 = new Player(1L, "Darek");

    PlayerData PLAYER_DATA_1 = new PlayerData(1L, "Darek", new HashMap<>());

    PlayerData PLAYER_DATA_2 = new PlayerData(2L, "Jony", new HashMap<>(Map.of(
            LocalDateTime.parse("2024-01-01T13:30:33"), 80)));

    PlayerDto PLAYER_DTO_1 = new PlayerDto(1L, "Darek");

    PlayerWithStatistic PLAYER_WITH_STATISTIC_1 = new PlayerWithStatistic(
            2L, "Jony",LocalDateTime.parse("2024-01-01T13:30:33"), 80);

    List<QuestWithAnswer> QUEST_WITH_ANSWER_1 = List.of(
            new QuestWithAnswer(
            1L,
            DifficultyLevel.A,
            "geography",
            "What is the name of the Polish sea?",
            "Caspian Sea",
            false),
            new QuestWithAnswer(
                    1L,
                    DifficultyLevel.A,
                    "geography",
                    "What is the name of the Polish sea?",
                    "Baltic Sea",
                    true),
            new QuestWithAnswer(
                    1L,
                    DifficultyLevel.A,
                    "geography",
                    "What is the name of the Polish sea?",
                    "Black Sea",
                    false)
            );


    QuestData QUEST_DATA_1 = new QuestData(
            1L, DifficultyLevel.A,
            "geography", "What is the name of the Polish sea?",
            Map.of(
                    "Caspian Sea", false,
                    "Baltic Sea", true,
                    "Black Sea", false));

    QuestData QUEST_DATA_2 = new QuestData(
            2L, DifficultyLevel.A,
            "science", "Popular phone in the USA?",
            Map.of(
                    "Nokia", false,
                    "iPhone", true,
                    "Android", false));

    QuestData QUEST_DATA_3 = new QuestData(
            3L, DifficultyLevel.B,
            "geography", "What is the name of the longest river in Poland?",
            Map.of(
                    "Vistula", true,
                    "Oder", false,
                    "Warta", false));

    QuestData QUEST_DATA_4 = new QuestData(
            4L, DifficultyLevel.A,
            "literature", "Who is the author of \"Crime and Punishment\"?",
            Map.of(
                    "Tolstoy", false,
                    "Fyodor Dostoevsky", true,
                    "Ivan Turgenev", false));
    QuestData QUEST_DATA_5 = new QuestData(
            5L, DifficultyLevel.A,
            "geography", "What is the name of the highest peak in the Polish Tatras?",
            Map.of(
                    "Kasprowy Wierch", false,
                    "Rysy", true,
                    "Swinica", false));

    QuestData QUEST_DATA_6 = new QuestData(
            6L, DifficultyLevel.A,
            "biology", "Popular color of a squirrel?",
            Map.of(
                    "Red", true,
                    "White", false,
                    "Black", false));

    QuestData QUEST_DATA_7 = new QuestData(
            7L, DifficultyLevel.B,
            "film", "Which Polish city is famous for hosting an annual film festival?",
            Map.of(
                    "Lodz", false,
                    "Szczecin", false,
                    "Gdynia", true));

    QuestData QUEST_DATA_8 = new QuestData(
            8L, DifficultyLevel.B,
            "history", "Who was the first Roman emperor?",
            Map.of(
                    "Julius Caesar", false,
                    "Nero", false,
                    "Augustus", true));

    QuestData QUEST_DATA_9 = new QuestData(
            9L, DifficultyLevel.B,
            "sport", "Who is currently considered the best football player in the world?",
            Map.of(
                    "Cristiano Ronaldo", false,
                    "Lionel Messi", true,
                    "Neymar", false));

    QuestData QUEST_DATA_10 = new QuestData(
            10L, DifficultyLevel.B,
            "film", "Which Polish city is famous for the \"Mozg\" horror film festival?",
            Map.of(
                    "Poznan", false,
                    "Gdansk", false,
                    "Bydgoszcz", true));

    QuestData QUEST_DATA_11 = new QuestData(
            11L, DifficultyLevel.C,
            "art", "Who is the author of the sculpture \"David\"?",
            Map.of(
                    "Michelangelo", true,
                    "Leonardo da Vinci", false,
                    "Pablo Picasso", false));

    QuestData QUEST_DATA_12 = new QuestData(
            12L, DifficultyLevel.C,
            "science", "Which chemical element has the symbol \"Na\"?",
            Map.of(
                    "Sodium", true,
                    "Potassium", false,
                    "Magnesium", false));

    QuestData QUEST_DATA_13 = new QuestData(
            13L, DifficultyLevel.C,
            "science", "What is the name of the largest ocean on Earth?",
            Map.of(
                    "Pacific", true,
                    "Atlantic", false,
                    "Indian", false));

    QuestData QUEST_DATA_14 = new QuestData(
            14L, DifficultyLevel.C,
            "science", "Which chemical element has the symbol \"Si\"?",
            Map.of(
                    "Selenium", false,
                    "Sodium", false,
                    "Silicon", true));

    QuestData QUEST_DATA_15 = new QuestData(
            15L, DifficultyLevel.C,
            "geography", "What is the highest mountain in Poland?",
            Map.of(
                    "Sniezka", false,
                    "Kasprowy Wierch", false,
                    "Rysy", true));

    Map<DifficultyLevel,List<QuestData>> QUEST_DATA_GROUPED_BY_DIFFICULTY_LEVEL = Map.of(
            DifficultyLevel.A,List.of(QUEST_DATA_1,QUEST_DATA_2,
            QUEST_DATA_4, QUEST_DATA_5, QUEST_DATA_6),
            DifficultyLevel.B, List.of(QUEST_DATA_3,QUEST_DATA_7,
                    QUEST_DATA_8, QUEST_DATA_9, QUEST_DATA_10),
            DifficultyLevel.C, List.of(QUEST_DATA_11, QUEST_DATA_12,
                    QUEST_DATA_13, QUEST_DATA_14, QUEST_DATA_15));

    QuestData QUEST_DATA_EMPTY_MAP = new QuestData(
            1L,
            DifficultyLevel.A,
            "geography",
            "What is the name of the Polish sea?",
            new HashMap<>());

    Quest QUEST_1 = new Quest(
            1L,
            DifficultyLevel.A,
            "geography",
            "What is the name of the Polish sea?"
    );

    List<Answer> ANSWERS_1 = List.of(
            new Answer(null, 1L, "Caspian Sea", false),
            new Answer(null, 1L, "Baltic Sea", true),
            new Answer(null, 1L, "Black Sea", false)
    );

    AnswersDto ANSWERS_DTO_1 = new AnswersDto(1L,
            Map.of(
                    1L, "Baltic Sea",
                    2L,"iPhone",
                    16L,"Oder",
                    4L,"Tolstoy",
                    5L,"Swinica",
                    6L,"Red"));

    QuestDto QUEST_DTO_1 = new QuestDto(
            1L, DifficultyLevel.A,
            "geography", "What is the name of the Polish sea?",
            Map.of(
                    "c","Caspian Sea",
                    "a","Baltic Sea",
                    "b","Black Sea"));

    QuestDto QUEST_DTO_2 = new QuestDto(
            2L, DifficultyLevel.A,
            "science", "Popular phone in the USA?",
            Map.of(
                    "b","Nokia",
                    "c","iPhone",
                    "a","Android"));

}

