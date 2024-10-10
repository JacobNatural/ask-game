package com.app.model;

import com.app.dto.QuestDto;
import com.app.model.difficulty_level.DifficultyLevel;
import com.app.model.player.PlayerData;
import com.app.model.quest.QuestData;
import com.app.repository.model.Answer;
import com.app.repository.model.Quest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ModelTestData {
    PlayerData PLAYER_DATA_1 = new PlayerData(
            1L,
            "A",
            new HashMap<>(Map.of(
                    LocalDateTime.parse("2024-04-10T22:20:15"), 80,
                    LocalDateTime.parse("2024-04-20T12:30:05"), 50,
                    LocalDateTime.parse("2024-05-12T15:45:10"), 85
            ))
    );

    PlayerData PLAYER_DATA_2 = new PlayerData(
            1L,
            "B",
            new HashMap<>(Map.of(
                    LocalDateTime.parse("2024-05-15T18:20:15"), 110,
                    LocalDateTime.parse("2024-06-01T17:30:05"), 20,
                    LocalDateTime.parse("2024-07-02T19:45:10"), 75
            ))
    );

    PlayerData PLAYER_DATA_3 = new PlayerData(
            1L,
            "C",
            new HashMap<>(Map.of(
                    LocalDateTime.parse("2024-05-01T12:20:15"), 90,
                    LocalDateTime.parse("2024-05-06T13:30:05"), 30,
                    LocalDateTime.parse("2024-05-10T13:45:10"), 105
            ))
    );

    QuestData QUEST_DATA_1 = new QuestData(
            1L,
            DifficultyLevel.A,
            "music",
            "Who composed \"Carmen\"?",
            new HashMap<>(Map.of(
                    "Wolfgang Amadeus Mozart", false,
                    "Johann Strauss II", false,
                    "Georges Bizet", true
            ))
    );

    QuestData QUEST_DATA_2 = new QuestData(
            2L,
            DifficultyLevel.A,
            "geography",
            "What is the name of the Polish sea?",
            new HashMap<>(Map.of(
                    "Caspian Sear", false,
                    "Baltic Sea", true,
                    "Black Sea", false
            ))
    );

    QuestData QUEST_DATA_3 = new QuestData(
            3L,
            DifficultyLevel.B,
            "geography",
            "What is the name of the longest river in Poland?",
            new HashMap<>(Map.of(
                    "Vistula", true,
                    "Oder", false,
                    "Warta", false
            )));

    Quest QUEST_1 = new Quest(
            1L,
            DifficultyLevel.A,
            "music",
            "Who composed \"Carmen\"?"
    );

    Quest QUEST_2 = new Quest(
            2L,
            DifficultyLevel.A,
            "geography",
            "What is the name of the Polish sea?"
    );

    Quest QUEST_3 = new Quest(
            3L,
            DifficultyLevel.B,
            "geography",
            "What is the name of the longest river in Poland?"
    );

    List<Answer> ANSWERS_1 = List.of(
            new Answer(null, 1L, "Wolfgang Amadeus Mozart", false),
            new Answer(null, 1L, "Johann Strauss II", false),
            new Answer(null, 1L, "Georges Bizet", true)
    );

    QuestDto QUEST_DTO_1 = new QuestDto(
            1L,
            DifficultyLevel.A,
            "music",
            "Who composed \"Carmen\"?",
            new HashMap<>(Map.of(
                    "a", "Georges Bizet",
                    "b", "Johann Strauss II",
                    "c", "Wolfgang Amadeus Mozart"
            ))
    );

}
