//package com.app.data_provider;
//
//import com.app.difficulty_level.DifficultyLevel;
//import com.app.dto.AnswersDto;
//import com.app.dto.QuestDto;
//import com.app.line_parser.impl.QuestParser;
//import com.app.player.PlayerData;
//import com.app.quest.QuestData;
//import com.app.quest.QuestMapper;
//import com.app.repository.impl.QuestRepositoryImpl;
//import com.app.txt.load.impl.QuestFileReader;
//import com.app.txt.model.PlayerData;
//import com.app.txt.transfer.impl.TransferImpl;
//import lombok.SneakyThrows;
//
//import java.nio.file.Paths;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public interface    DataProvider {
//    String FILENAME_TRANSFER_LOAD = "transfer_test.txt";
//    String FILENAME_SAVE = "save_test.txt";
//    String FILENAME_PLAYER = "player.txt";
//    String FILENAME_QUEST = "quest.txt";
//    String FILENAME_QUESTS = "quests.txt";
//    String FILENAME_PLAYERS = "players.txt";
//    String FILENAME_EMPTY_FILE = "empty_file.txt";
//    String FILENAME_REPOSITORY_QUEST = "repository_quest.txt";
//    String QUEST_BIG_DATA = "quests_more_data.txt";
//
//    @SneakyThrows
//    static String getPath(String filename) {
//        return Paths.get(
//                DataProvider.class.getClassLoader().getResource(filename).toURI()).toString();
//    }
//
//    List<String> TEXT_SAVE = List.of("simple_text.txt");
//
//    PlayerData PLAYER_DATA_1 = new PlayerData(
//            "Darek",
//            new HashMap<>(Map.of(
//                    LocalDateTime.of(
//                            LocalDate.of(2022, 2, 1),
//                            LocalTime.of(22, 22)
//                    ), 60
//            ))
//    );
//
//    PlayerData PLAYER_DATA1 = new PlayerData("Darek",
//            new HashMap<>(Map.of(
//                    LocalDateTime.of(
//                            LocalDate.of(2022, 2, 1),
//                            LocalTime.of(22, 22)
//                    ), 60
//            ))
//    );
//
//    PlayerData PLAYER_DATA_2 = new PlayerData(
//            "Halina",
//            new HashMap<>(Map.of(
//                    LocalDateTime.of(
//                            LocalDate.of(2024, 4, 16),
//                            LocalTime.of(14, 2, 47)
//                    ), 150,
//                    LocalDateTime.of(
//                            LocalDate.of(2024, 4, 20),
//                            LocalTime.of(14, 2, 47)
//                    ), 50
//            ))
//    );
//
//    PlayerData PLAYER_DATA_3 = new PlayerData(
//            "Szynszyl",
//            new HashMap<>(Map.of(
//                    LocalDateTime.of(
//                            LocalDate.of(2024, 4, 16),
//                            LocalTime.of(14, 3, 8)
//                    ), 90
//            ))
//    );
//
//    PlayerData PLAYER_DATA_4 = new PlayerData(
//            "Jakub",
//            new HashMap<>(Map.of(
//                    LocalDateTime.of(
//                            LocalDate.of(2024, 4, 29),
//                            LocalTime.of(18, 20, 23)
//                    ), 90
//            ))
//    );
//
//    PlayerData PLAYER_DATA_5 = new PlayerData(
//            "Jakub",
//            new HashMap<>(Map.of(
//                    LocalDateTime.of(
//                            LocalDate.of(2024, 4, 30),
//                            LocalTime.of(19, 20, 23)
//                    ), 80
//            ))
//    );
//
//    PlayerData PLAYER_DATA_6 = new PlayerData(
//            "Jakub",
//            new HashMap<>(Map.of(
//                    LocalDateTime.of(
//                            LocalDate.of(2024, 4, 29),
//                            LocalTime.of(18, 20, 23)
//                    ), 90,
//                    LocalDateTime.of(
//                            LocalDate.of(2024, 4, 30),
//                            LocalTime.of(19, 20, 23)
//                    ), 80
//            ))
//    );
//
//    PlayerData PLAYER_DATA_7 = new PlayerData(
//            "Jakub",
//            new HashMap<>(Map.of(
//                    LocalDateTime.of(
//                            LocalDate.of(2024, 4, 29),
//                            LocalTime.of(18, 20, 23)
//                    ), 90,
//                    LocalDateTime.of(
//                            LocalDate.of(2024, 4, 30),
//                            LocalTime.of(19, 20, 23)
//                    ), 80,
//                    LocalDateTime.of(
//                            LocalDate.of(2024, 7, 30),
//                            LocalTime.of(19, 20, 23)
//                    ), 150)
//            )
//    );
//
//    static Map<String, Boolean> getAnswer1() {
//        Map<String, Boolean> answer1 = new LinkedHashMap<>();
//        answer1.put("Nokia", false);
//        answer1.put("iPhone", true);
//        answer1.put("Android", false);
//        return answer1;
//    }
//
//    QuestData QUEST_DATA_DATA_1 = new QuestData(
//            DifficultyLevel.A, "science",
//            "Popular phone in the USA?",
//            getAnswer1()
//    );
//
//    com.app.txt.model.QuestData QUEST_DATA_DATA_1 = new com.app.txt.model.QuestData(DifficultyLevel.A, "science",
//            "Popular phone in the USA?", getAnswer1());
//
//    static Map<String, Boolean> getAnswer2() {
//        Map<String, Boolean> answer1 = new LinkedHashMap<>();
//        answer1.put("Caspian Sear", false);
//        answer1.put("Baltic Sea", true);
//        answer1.put("Black Sea", false);
//        return answer1;
//    }
//
//    QuestData QUEST_DATA_DATA_2 = new QuestData(DifficultyLevel.A, "geography",
//            "What is the name of the Polish sea?",
//            getAnswer2());
//
//    static Map<String, Boolean> getAnswer3() {
//        Map<String, Boolean> answer1 = new LinkedHashMap<>();
//        answer1.put("Vistula", true);
//        answer1.put("Oder", false);
//        answer1.put("Warta", false);
//        return answer1;
//    }
//
//    QuestData QUEST_DATA_DATA_3 = new QuestData(DifficultyLevel.B, "geography",
//            "What is the name of the longest river in Poland?",
//            getAnswer3());
//
//    static Map<String, Boolean> getAnswer4() {
//        Map<String, Boolean> answer1 = new LinkedHashMap<>();
//        answer1.put("Fiat", true);
//        answer1.put("Mercedes", false);
//        answer1.put("Ford", false);
//        return answer1;
//    }
//
//    QuestData QUEST_DATA_DATA_4 = new QuestData(DifficultyLevel.B, "history",
//            "Car manufactured in Poland?",
//            getAnswer4());
//
//    @SneakyThrows
//    static List<com.app.txt.model.QuestData> questDataProvider() {
//        var filename = Paths.get(
//                DataProvider.class.getClassLoader().getResource(FILENAME_REPOSITORY_QUEST).toURI()).toString();
//
//        var transfer = new TransferImpl<com.app.txt.model.QuestData>();
//        var questParser = new QuestParser(".*", ";",1);
//        var questLoad = new QuestFileReader(transfer, questParser);
//
//        return questLoad.load(filename);
//    }
//
//    QuestDto QUEST_DTO_1 = new QuestDto(1L,
//            DifficultyLevel.A, "science",
//            "Popular phone in the USA?",
//            Map.of(
//                    "a","Nokia",
//                    "b", "iPhone",
//                    "c", "Android")
//    );
//
//    QuestDto QUEST_DTO_2 = new QuestDto(2L,
//            DifficultyLevel.A, "geography",
//            "What is the name of the Polish sea?",
//            Map.of(
//                    "a","Caspian Sear",
//                    "b", "Baltic Sea",
//                    "c", "Black Sea")
//    );
//
//    QuestDto QUEST_DTO_3 = new QuestDto(3L,
//            DifficultyLevel.B, "geography",
//            "What is the name of the longest river in Poland?",
//            Map.of(
//                    "a","Vistula",
//                    "b", "Oder",
//                    "c", "Warta")
//    );
//
//    QuestDto QUEST_DTO_4 = new QuestDto(4L,
//            DifficultyLevel.B, "history",
//            "Car manufactured in Poland?",
//            Map.of(
//                    "a","Fiat",
//                    "b", "Mercedes",
//                    "c", "Ford")
//    );
//
//    static Map<Long, String> answerDTO(){
//        var answer = new LinkedHashMap<Long, String>();
//        answer.put(1L, "iPhone");
//        answer.put(2L, "Black Sea");
//        answer.put(3L, "Vistula");
//
//        return answer;
//    }
//
//    AnswersDto ANSWERS_DTO_1 = new AnswersDto(
//            1L, answerDTO());
//
//    @SneakyThrows
//    static Map<DifficultyLevel, List<QuestData>> QUEST_FOR_ANSWERS (){
//
//        var transfer = new TransferImpl<com.app.txt.model.QuestData>();
//        var questParser = new QuestParser(".*", ";",1);
//        var questLoad = new QuestFileReader(transfer, questParser);
//        var questRepository = new QuestRepositoryImpl(getPath(QUEST_BIG_DATA), questLoad);
//
//        return questRepository
//                .getAll()
//                .values()
//                .stream()
//                .collect(Collectors.groupingBy(QuestMapper.toDifficulty));
//    }
//}
