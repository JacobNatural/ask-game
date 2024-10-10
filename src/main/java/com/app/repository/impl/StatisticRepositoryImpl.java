package com.app.repository.impl;

import com.app.repository.StatisticRepository;
import com.app.repository.impl.generic.impl.AbstractCrudRepository;
import com.app.repository.model.Statistic;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the StatisticRepository interface for managing player statistics.
 * This class provides methods to retrieve and delete statistics associated with players.
 */
@Repository
public class StatisticRepositoryImpl extends AbstractCrudRepository<Statistic, Long> implements StatisticRepository {
    public StatisticRepositoryImpl(Jdbi jdbi) {
        super(jdbi);
    }

    /**
     * Retrieves a list of statistics associated with a specific player ID.
     *
     * @param playerId The ID of the player whose statistics are to be retrieved.
     * @return A list of Statistic objects related to the specified player ID.
     */
    public List<Statistic> findByPlayerId(Long playerId) {
        var sql = "SELECT * FROM statistics WHERE player_id = :playerId";

        return jdbi.withHandle(handle ->
                handle
                        .createQuery(sql)
                        .bind("playerId", playerId)
                        .mapToBean(Statistic.class)
                        .list());
    }

    /**
     * Deletes statistics associated with a specific player ID and returns the deleted statistics.
     *
     * @param playerId The ID of the player whose statistics are to be deleted.
     * @return A list of Statistic objects that were deleted, associated with the specified player ID.
     */
    public List<Statistic> deleteByPlayerId(Long playerId) {
        var sql = "delete from statistics where player_id = :playerId";

        var statistic = findByPlayerId(playerId);

        jdbi.useHandle(handle ->
                handle.createUpdate(sql)
                        .bind("playerId", playerId)
                        .execute());

        return statistic;
    }
}
