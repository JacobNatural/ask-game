package com.app.difficulty_level;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum representing the difficulty levels of quests or challenges.
 *
 * <p>This enum provides three difficulty levels:
 * <ul>
 *     <li>{@link #A} - Represents the easiest level.</li>
 *     <li>{@link #B} - Represents a medium level of difficulty.</li>
 *     <li>{@link #C} - Represents the hardest level.</li>
 * </ul>
 *
 * <p>It uses Lombok's {@link RequiredArgsConstructor} to generate a constructor,
 * and {@link Getter} to generate getter methods for the enum constants.
 */
@RequiredArgsConstructor
@Getter
public enum DifficultyLevel {
    A, // Easiest difficulty
    B, // Medium difficulty
    C  // Hardest difficulty
}