package com.app.txt.load;

import java.util.List;

/**
 * Interface for loading data from a file.
 *
 * @param <T> the type of data to be loaded
 */
public interface FileReader<T> {

    /**
     * Loads a list of data of type T from the specified file.
     *
     * @param filename the name of the file to load data from
     * @return a list of loaded data of type T
     */
    List<T> load(String filename);
}