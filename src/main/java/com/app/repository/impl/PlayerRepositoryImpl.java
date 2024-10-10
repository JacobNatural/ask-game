package com.app.repository.impl;

import com.app.repository.PlayerRepository;
import com.app.repository.impl.generic.impl.AbstractCrudRepository;
import com.app.repository.model.Player;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the PlayerRepository interface using JDBI for database operations.
 * Extends the AbstractCrudRepository to provide CRUD functionalities for Player entities.
 */
@Repository
public class PlayerRepositoryImpl extends AbstractCrudRepository<Player, Long> implements PlayerRepository {

    /**
     * Constructs a PlayerRepositoryImpl with the specified Jdbi instance.
     *
     * @param jdbi The Jdbi instance for executing database operations.
     */
    public PlayerRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    /**
     * Retrieves the ranking of players based on their maximum scores.
     *
     * @return A map where the key is the player's maximum score and the value is the player's login.
     *         The map is ordered by maximum score in descending order.
     */
    public Map<Integer, String> getPlayersRanking() {
        var sql = """
                SELECT max(s.score) AS max_score, p.login 
                FROM players p 
                JOIN statistics s ON p.id = s.player_id 
                GROUP BY p.login 
                ORDER BY max_score DESC;
                """;

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .map(m -> new AbstractMap.SimpleEntry<>(
                                m.getColumn("max_score", Integer.class),
                                m.getColumn("login", String.class)))
                        .stream()
                        .collect(Collectors.toMap(
                                AbstractMap.SimpleEntry::getKey,
                                AbstractMap.SimpleEntry::getValue,
                                (v1, v2) -> String.format("%s, %s", v1, v2), // Fixed string concatenation
                                LinkedHashMap::new
                        )));
    }
}
