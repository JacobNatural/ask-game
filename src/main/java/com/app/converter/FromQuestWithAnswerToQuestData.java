package com.app.converter;

import com.app.model.quest.QuestData;
import com.app.repository.model.Quest;
import com.app.repository.model.QuestWithAnswer;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Interface for converting a list of {@link QuestWithAnswer} objects into {@link QuestData} objects.
 * This interface extends the {@link Converter} interface, specifying the source and target types.
 */
public interface FromQuestWithAnswerToQuestData extends Converter<QuestWithAnswer, QuestData> {

    /**
     * Converts a list of {@link QuestWithAnswer} entities into a list of {@link QuestData}.
     *
     * @param entityType List of {@link QuestWithAnswer} entities to convert.
     * @return List of {@link QuestData} containing quest details and associated answers.
     */
    List<QuestData> toList(List<QuestWithAnswer> entityType);

    /**
     * Converts a list of {@link QuestWithAnswer} entities into a map where the keys are generated
     * using the provided mapper function and the values are the corresponding {@link QuestData} objects.
     *
     * @param playerWithStatistics List of {@link QuestWithAnswer} entities to convert.
     * @param mapper Function that maps a {@link Quest} to a key of type T.
     * @param <T> The type of the key in the resulting map.
     * @return A map where the keys are generated using the mapper function and values are the corresponding {@link QuestData} objects.
     */
    <T> Map<T, QuestData> toMap(List<QuestWithAnswer> playerWithStatistics, Function<Quest, T> mapper);

    /**
     * Groups a list of {@link QuestWithAnswer} entities by keys (defined by the provided mapper)
     * and returns a map where each key corresponds to a list of {@link QuestData}.
     *
     * @param playerWithStatistics List of {@link QuestWithAnswer} entities to convert.
     * @param mapper Function that maps a {@link Quest} to a key of type T.
     * @param <T> The type of the key in the resulting map.
     * @return A map where the keys are generated using the mapper function and values are lists of corresponding {@link QuestData} objects.
     */
    <T> Map<T, List<QuestData>> toGroupBy(List<QuestWithAnswer> playerWithStatistics, Function<Quest, T> mapper);
}