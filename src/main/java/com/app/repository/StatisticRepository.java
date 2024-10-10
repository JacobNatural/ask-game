package com.app.repository;

import com.app.repository.impl.generic.CrudRepository;
import com.app.repository.model.Statistic;

import java.util.List;

/**
 * Repository interface for managing Statistic entities.
 * This interface provides methods to perform CRUD operations
 * and retrieve statistics related to players.
 */
public interface StatisticRepository extends CrudRepository<Statistic, Long> {

    /**
     * Finds all statistics associated with a specific player ID.
     *
     * @param playerId the ID of the player for whom to find statistics
     * @return a list of Statistic objects associated with the specified player ID
     */
    List<Statistic> findByPlayerId(Long playerId);

    /**
     * Deletes all statistics associated with a specific player ID.
     *
     * @param playerId the ID of the player for whom to delete statistics
     * @return a list of Statistic objects that were deleted, associated with the specified player ID
     */
    List<Statistic> deleteByPlayerId(Long playerId);
}
