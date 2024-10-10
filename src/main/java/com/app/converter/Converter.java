package com.app.converter;

import java.util.List;

/**
 * A generic interface for converting a list of objects of one type to a list of another type.
 *
 * @param <T> the source type of the objects to be converted.
 * @param <U> the target type of the objects after conversion.
 */
public interface Converter<T, U> {

    /**
     * Converts a list of objects of type T to a list of objects of type U.
     *
     * @param entityType the list of objects of type T to be converted.
     * @return a list of converted objects of type U.
     */
    List<U> toList(List<T> entityType);
}