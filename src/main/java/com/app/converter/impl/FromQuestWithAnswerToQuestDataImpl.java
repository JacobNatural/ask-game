package com.app.converter.impl;

import com.app.converter.FromQuestWithAnswerToQuestData;
import com.app.model.quest.QuestData;
import com.app.repository.model.Quest;
import com.app.repository.model.QuestWithAnswer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link FromQuestWithAnswerToQuestData} interface, responsible for converting
 * a list of {@link QuestWithAnswer} entities into {@link QuestData} objects.
 */
@Component
public class FromQuestWithAnswerToQuestDataImpl implements FromQuestWithAnswerToQuestData {

    /**
     * Converts a list of {@link QuestWithAnswer} entities into a list of {@link QuestData}.
     * The resulting {@link QuestData} objects contain the quest information along with their associated answers.
     *
     * @param entityType List of {@link QuestWithAnswer} entities to convert.
     * @return List of {@link QuestData} containing quest details and associated answers.
     */
    public List<QuestData> toList(List<QuestWithAnswer> entityType) {
        return mergeData(entityType)
                .entrySet()
                .stream()
                .map(m -> new QuestData(m.getKey(), m.getValue()))
                .toList();
    }

    /**
     * Converts a list of {@link QuestWithAnswer} entities into a map of keys (defined by the provided mapper)
     * and their corresponding {@link QuestData} objects.
     *
     * @param playerWithStatistics List of {@link QuestWithAnswer} entities to convert.
     * @param mapper Function that maps a {@link Quest} to a key of type T.
     * @param <T> The type of the key in the resulting map.
     * @return A map where the keys are generated using the mapper function and values are the corresponding {@link QuestData} objects.
     */
    public <T> Map<T, QuestData> toMap(List<QuestWithAnswer> playerWithStatistics, Function<Quest, T> mapper) {
        return mergeData(playerWithStatistics)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        m -> mapper.apply(m.getKey()),
                        m -> new QuestData(m.getKey(), m.getValue())
                ));
    }

    /**
     * Groups a list of {@link QuestWithAnswer} entities by keys (defined by the provided mapper)
     * and returns a map where each key corresponds to a list of {@link QuestData}.
     *
     * @param playerWithStatistics List of {@link QuestWithAnswer} entities to convert.
     * @param mapper Function that maps a {@link Quest} to a key of type T.
     * @param <T> The type of the key in the resulting map.
     * @return A map where the keys are generated using the mapper function and values are lists of corresponding {@link QuestData} objects.
     */
    public <T> Map<T, List<QuestData>> toGroupBy(List<QuestWithAnswer> playerWithStatistics, Function<Quest, T> mapper) {
        return mergeData(playerWithStatistics)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        m -> mapper.apply(m.getKey()),
                        m -> new ArrayList<>(List.of(new QuestData(m.getKey(), m.getValue()))),
                        (v1, v2) -> {
                            v1.addAll(v2);
                            return v1;
                        }
                ));
    }

    /**
     * Merges the data from a list of {@link QuestWithAnswer} entities into a map where each key is a {@link Quest}
     * and the value is a map of answers and their confirmation status.
     *
     * @param questWithAnswers List of {@link QuestWithAnswer} entities to merge.
     * @return A map where keys are {@link Quest} objects and values are maps of answers (string) to their confirmation status (boolean).
     */
    private Map<Quest, Map<String, Boolean>> mergeData(List<QuestWithAnswer> questWithAnswers) {
        return questWithAnswers
                .stream()
                .collect(Collectors.toMap(
                        s -> new Quest(
                                s.getId(), s.getDifficultyLevel(),
                                s.getCategory(), s.getQuestion()),
                        s -> new HashMap<>(Map.of(s.getAnswer(), s.getConfirm())),
                        (v1, v2) -> {
                            v1.putAll(v2);
                            return v1;
                        }
                ));
    }
}