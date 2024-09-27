package com.app.quest;

import com.app.difficulty_level.DifficultyLevel;

import java.util.function.Function;
import java.util.function.ToLongFunction;

/**
 * Provides mapping functions for the {@link Quest} class.
 *
 * <p>This interface contains various functional interfaces that allow easy access to specific properties
 * of a {@link Quest} instance, such as its question text, difficulty level, and ID.</p>
 */
public interface QuestMapper {

    /**
     * A function that retrieves the question text from a {@link Quest}.
     */
    Function<Quest, String> toQuestion = quest -> quest.question;

    /**
     * A function that retrieves the difficulty level from a {@link Quest}.
     */
    Function<Quest, DifficultyLevel> toDifficulty = quest -> quest.difficultyLevel;

    /**
     * A function that retrieves the ID from a {@link Quest}.
     */
    ToLongFunction<Quest> toId = quest -> quest.id;
}