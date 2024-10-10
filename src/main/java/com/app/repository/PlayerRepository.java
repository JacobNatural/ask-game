package com.app.repository;

import com.app.repository.impl.generic.CrudRepository;
import com.app.repository.model.Player;

import java.util.Map;

/**
 * Repository interface for managing Player entities.
 * Extends the generic CrudRepository for basic CRUD operations
 * and provides additional methods specific to Player operations.
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {

    /**
     * Retrieves the ranking of players based on their scores.
     * The ranking is represented as a map where the key is the score
     * and the value is the player's login.
     *
     * @return a Map containing player scores and their corresponding logins
     */
    Map<Integer, String> getPlayersRanking();
}