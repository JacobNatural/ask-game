package com.app.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a player in the application.
 * This class contains the player's unique identifier and login information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    /**
     * The unique identifier for the player.
     */
    private Long id;

    /**
     * The login name of the player.
     * This is used for authentication and identification purposes.
     */
    private String login;
}