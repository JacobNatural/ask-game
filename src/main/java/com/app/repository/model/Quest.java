package com.app.repository.model;

import com.app.model.difficulty_level.DifficultyLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a quest in the application.
 * A quest contains a unique identifier, its difficulty level,
 * category, and the question associated with the quest.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quest {

    /**
     * The unique identifier for the quest.
     */
    private Long id;

    /**
     * The difficulty level of the quest.
     * This indicates how challenging the quest is for the player.
     */
    private DifficultyLevel difficultyLevel;

    /**
     * The category to which the quest belongs.
     * This can be used to group similar quests together.
     */
    private String category;

    /**
     * The question posed by the quest.
     * This is the main content that players will interact with.
     */
    private String question;
}