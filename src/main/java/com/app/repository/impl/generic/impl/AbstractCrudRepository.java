package com.app.repository.impl.generic.impl;

import com.app.model.difficulty_level.DifficultyLevel;
import com.app.repository.impl.generic.CrudRepository;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import org.atteo.evo.inflector.English;
import org.jdbi.v3.core.Jdbi;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Abstract implementation of a CRUD repository, providing generic methods for
 * persisting and retrieving entities from a database using JDBI.
 *
 * @param <T> The type of the entity managed by this repository.
 * @param <ID> The type of the entity's identifier.
 */
@RequiredArgsConstructor
public class AbstractCrudRepository<T, ID> implements CrudRepository<T, ID> {
    protected final Jdbi jdbi;

    private final Class<T> entityType =
            (Class<T>) ((ParameterizedType) super.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    /**
     * Saves a new entity to the database.
     *
     * @param item The entity to be saved.
     * @return The saved entity with its generated ID.
     */
    @Override
    public T save(T item) {
        var sql = "insert into %s %s values %s;".formatted(
                tableName(),
                columnNamesForInsert(),
                columnValuesForInsert(item));

        jdbi.useHandle(handle -> handle.execute(sql));

        return findLast(1).getFirst();
    }

    /**
     * Saves a list of entities to the database.
     *
     * @param items The list of entities to be saved.
     * @return A list of saved entities with their generated IDs.
     */
    @Override
    public List<T> saveAll(List<T> items) {
        var sql = "insert into %s %s values %s;".formatted(
                tableName(),
                columnNamesForInsert(),
                items
                        .stream()
                        .map(this::columnValuesForInsert)
                        .collect(Collectors.joining(", ")));

        jdbi.useHandle(handle -> handle.execute(sql));

        return findLast(items.size());
    }

    /**
     * Deletes an entity from the database by its ID.
     *
     * @param id The ID of the entity to be deleted.
     * @return The deleted entity.
     * @throws IllegalArgumentException If the entity with the specified ID is not found.
     */
    @Override
    public T delete(ID id) {
        var sql = "delete from %s where id = :id;".formatted(tableName());

        var itemToDelete = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id not found"));
        jdbi.useHandle(handle ->
                handle
                        .createUpdate(sql)
                        .bind("id", id)
                        .execute()
        );

        return itemToDelete;
    }

    /**
     * Finds an entity by its ID.
     *
     * @param id The ID of the entity to be found.
     * @return An Optional containing the found entity, or an empty Optional if no entity is found.
     */
    @Override
    public Optional<T> findById(ID id) {
        var sql = "select * from " + tableName() + " where id = :id";

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .mapToBean(entityType)
                        .findFirst());
    }

    /**
     * Finds the last N entities in the database, ordered by their IDs in descending order.
     *
     * @param n The number of entities to retrieve.
     * @return A list of the last N entities.
     */
    @Override
    public List<T> findLast(int n) {
        var sql = "select * from %s order by id desc limit :n".formatted(tableName());

        return jdbi.withHandle(handle ->
                handle
                        .createQuery(sql)
                        .bind("n", n)
                        .mapToBean(entityType)
                        .list());
    }

    /**
     * Finds all entities with the specified IDs.
     *
     * @param ids A list of IDs of the entities to be found.
     * @return A list of entities with the specified IDs.
     */
    @Override
    public List<T> findAllById(List<ID> ids) {
        var sql = "select * from %s where id in (<ids>)".formatted(tableName());

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bindList("ids", ids)
                        .mapToBean(entityType)
                        .list());
    }

    /**
     * Finds all entities in the database.
     *
     * @return A list of all entities.
     */
    @Override
    public List<T> findAll() {
        var sql = "select * from %s".formatted(tableName());
        return jdbi.withHandle(handle ->
                handle
                        .createQuery(sql)
                        .mapToBean(entityType)
                        .list());
    }

    /**
     * Converts a string from UpperCamelCase to lower_underscore format.
     *
     * @param upperCamel The string to be converted.
     * @return The converted string in lower_underscore format.
     */
    private String toLowerUnderScore(String upperCamel) {
        return CaseFormat.UPPER_CAMEL.to(
                CaseFormat.LOWER_UNDERSCORE,
                upperCamel);
    }

    /**
     * Retrieves the table name corresponding to the entity type.
     *
     * @return The pluralized table name in lower_underscore format.
     */
    private String tableName() {
        return English.plural(CaseFormat.UPPER_CAMEL.to(
                CaseFormat.LOWER_UNDERSCORE,
                entityType.getSimpleName()));
    }

    /**
     * Constructs a comma-separated list of column names for insertion, excluding the ID column.
     *
     * @return A string representing the column names for insertion.
     */
    private String columnNamesForInsert() {
        var cols = Arrays
                .stream(entityType.getDeclaredFields())
                .filter(field -> !field.getName().equalsIgnoreCase("id"))
                .map(field -> toLowerUnderScore(field.getName()))
                .collect(Collectors.joining(", "));
        return "( %s )".formatted(cols);
    }

    /**
     * Constructs a string of column values for insertion into the database.
     *
     * @param item The entity for which to retrieve column values.
     * @return A string representing the values to be inserted, formatted for SQL.
     */
    private String columnValuesForInsert(T item) {
        var values = Arrays
                .stream(entityType.getDeclaredFields())
                .filter(field -> !field.getName().equalsIgnoreCase("id"))
                .map(field -> {
                    try {
                        field.setAccessible(true);

                        if (field.get(item) == null) {
                            return "NULL";
                        }

                        if (List.of(
                                String.class,
                                Enum.class,
                                DifficultyLevel.class,
                                LocalDateTime.class,
                                LocalDate.class).contains(field.getType())) {
                            return "'%s'".formatted(field.get(item));
                        }
                        return field.get(item).toString();
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Getting columns value failed", e);
                    }
                }).collect(Collectors.joining(", "));
        return "( %s )".formatted(values);
    }
}
