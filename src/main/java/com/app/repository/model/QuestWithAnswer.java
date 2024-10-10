package com.app.repository.model;

import com.app.model.difficulty_level.DifficultyLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a quest along with its associated answer.
 * This class encapsulates the details of a quest, including
 * its question, difficulty level, category, and the answer
 * provided by the player.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestWithAnswer {

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

    /**
     * The answer provided by the player.
     * This is the response to the question posed by the quest.
     */
    private String answer;

    /**
     * Indicates whether the provided answer is confirmed as correct.
     * This value can be used to check if the answer is valid.
     */
    private Boolean confirm;
}