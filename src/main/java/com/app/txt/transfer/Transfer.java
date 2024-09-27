package com.app.txt.transfer;

import com.app.line_parser.LineParser;

import java.util.List;
import java.util.function.Function;

/**
 * Interface for transferring data to and from files.
 *
 * @param <T> the type of data to be transferred
 */
public interface Transfer<T> {

    /**
     * Loads data from a specified file using a provided line parser.
     *
     * @param filename the name of the file to load data from
     * @param parser   the line parser used to parse each line into an object of type T
     * @return a list of parsed data items of type T
     */
    List<T> load(String filename, LineParser<T> parser);

    /**
     * Saves a list of data items to a specified file using a formatting function.
     *
     * @param filename    the name of the file to save data to
     * @param data        the list of data items to be saved
     * @param toTxtFormat a function that converts each data item of type T to a String
     */
    void save(String filename, List<T> data, Function<T, String> toTxtFormat);
}