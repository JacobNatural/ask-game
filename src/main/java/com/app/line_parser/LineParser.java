package com.app.line_parser;

/**
 * Interface for parsing lines of text into objects of type {@code T}.
 *
 * @param <T> the type of object produced by the parser
 */
public interface LineParser<T> {

    /**
     * Parses a line of text into an object of type {@code T}.
     *
     * @param input the line of text to parse
     * @return an object of type {@code T} containing the parsed data
     */
    T parse(String input);
}