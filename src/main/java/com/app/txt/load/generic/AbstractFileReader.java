package com.app.txt.load.generic;

import com.app.line_parser.LineParser;
import com.app.txt.load.FileReader;
import com.app.txt.transfer.Transfer;
import lombok.AllArgsConstructor;

/**
 * Abstract class for loading data from a text source.
 *
 * <p>This class provides a base implementation for loading entities of type T
 * using a specified parser and data transfer mechanism.</p>
 *
 * @param <T> the type of entities to be loaded
 */
@AllArgsConstructor
public abstract class AbstractFileReader<T> implements FileReader<T> {

    /**
     * The transfer mechanism used to handle data loading.
     */
    protected Transfer<T> transfer;

    /**
     * The line parser used to parse text lines into entities of type T.
     */
    protected LineParser<T> parser;

    // Additional methods for loading data can be added here
}