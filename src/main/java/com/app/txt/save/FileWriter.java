package com.app.txt.save;

import java.util.List;
import java.util.function.Function;

/**
 * Interface for writing data to a file.
 *
 * @param <T> the type of data to be written to the file
 */
public interface FileWriter<T> {

    /**
     * Saves the provided data to a file with the specified filename.
     *
     * @param filename the name of the file where the data will be saved
     * @param data     the list of data items to be written to the file
     * @param function a function that converts each data item of type T to a String representation
     */
    void save(String filename, List<T> data, Function<T, String> function);
}