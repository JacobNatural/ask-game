package com.app.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an answer to a quiz question.
 * This class holds the information related to a specific answer
 * provided for a particular question (quest).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    /**
     * The unique identifier for the answer.
     */
    private Long id;

    /**
     * The identifier of the question (quest) this answer is associated with.
     */
    private Long questId;

    /**
     * The text of the answer.
     */
    private String answer;

    /**
     * Indicates whether the answer is confirmed as correct.
     */
    private boolean confirm;
}
