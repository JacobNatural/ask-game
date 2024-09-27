package com.app.line_parser.impl.generic;

import com.app.line_parser.LineParser;
import lombok.RequiredArgsConstructor;

/**
 * Abstract base class for line parsers that implements the {@link LineParser} interface.
 *
 * <p>This class provides common functionality for line parsing, including validation
 * of lines based on a specified regular expression and separator.</p>
 *
 * @param <T> the type of object produced by the line parser
 */
@RequiredArgsConstructor
public abstract class LineParserAbstract<T> implements LineParser<T> {
    private final String regex;
    protected final String separator;

    /**
     * Validates a line of text based on predefined criteria.
     *
     * <p>This method checks that the line is not {@code null}, not empty, and matches
     * the specified regular expression. If any of these conditions are not met,
     * an {@link IllegalArgumentException} is thrown.</p>
     *
     * @param line the line of text to validate
     * @throws IllegalArgumentException if the line is {@code null}, empty, or does not match the regex
     */
    protected void validateLine(String line) {
        if (line == null) {
            throw new IllegalArgumentException("Line cannot be null");
        }

        if (line.isEmpty()) {
            throw new IllegalArgumentException("Line cannot be empty");
        }

        if (!line.matches(regex)) {
            throw new IllegalArgumentException("Line does not match regex: " + line);
        }
    }
}