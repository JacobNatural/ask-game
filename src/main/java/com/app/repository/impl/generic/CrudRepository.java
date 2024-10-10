package com.app.repository.impl.generic;

import java.util.List;
import java.util.Optional;

/**
 * Generic interface for CRUD (Create, Read, Update, Delete) operations.
 *
 * @param <T> The type of the entity managed by the repository.
 * @param <ID> The type of the entity's identifier.
 */
public interface CrudRepository<T, ID> {

    /**
     * Saves a new entity to the repository.
     *
     * @param item The entity to be saved.
     * @return The saved entity with its generated ID.
     */
    T save(T item);

    /**
     * Deletes an entity from the repository by its ID.
     *
     * @param id The ID of the entity to be deleted.
     * @return The deleted entity.
     */
    T delete(ID id);

    /**
     * Retrieves all entities from the repository.
     *
     * @return A list of all entities.
     */
    List<T> findAll();

    /**
     * Retrieves the last N entities from the repository, ordered by their IDs in descending order.
     *
     * @param n The number of entities to retrieve.
     * @return A list of the last N entities.
     */
    List<T> findLast(int n);

    /**
     * Finds an entity by its ID.
     *
     * @param id The ID of the entity to be found.
     * @return An Optional containing the found entity, or an empty Optional if no entity is found.
     */
    Optional<T> findById(ID id);

    /**
     * Finds all entities with the specified IDs.
     *
     * @param ids A list of IDs of the entities to be found.
     * @return A list of entities with the specified IDs.
     */
    List<T> findAllById(List<ID> ids);

    /**
     * Saves a list of entities to the repository.
     *
     * @param items The list of entities to be saved.
     * @return A list of saved entities with their generated IDs.
     */
    List<T> saveAll(List<T> items);
}
