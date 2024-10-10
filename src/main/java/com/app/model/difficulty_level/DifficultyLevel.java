package com.app.model.difficulty_level;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * An enumeration representing the difficulty levels of quests.
 * <p>
 * This enum provides a way to categorize quests based on their complexity or challenge level.
 * The levels are defined as follows:
 * </p>
 * <ul>
 *     <li>{@link #A} - Represents the easiest difficulty level.</li>
 *     <li>{@link #B} - Represents a moderate difficulty level.</li>
 *     <li>{@link #C} - Represents the hardest difficulty level.</li>
 * </ul>
 */
@RequiredArgsConstructor
@Getter
public enum DifficultyLevel {
    A,
    B,
    C
}