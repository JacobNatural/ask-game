package com.app.line_parser.impl;

import com.app.line_parser.LineParser;
import com.app.line_parser.impl.generic.LineParserAbstract;
import com.app.txt.model.PlayerData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Concrete implementation of a line parser for parsing player data.
 *
 * <p>This class extends {@link LineParserAbstract} and implements the {@link LineParser}
 * interface to convert a line of text into a {@link PlayerData} object.</p>
 */
@Component
public class PlayerParser extends LineParserAbstract<PlayerData> implements LineParser<PlayerData> {

    /**
     * Constructs a new PlayerParser with the specified regex and separator.
     *
     * @param regex     the regular expression used for validating player data lines
     * @param separator the separator used in the player data line
     */
    public PlayerParser(
            @Value("${playerRegex}") String regex,
            @Value("${playerParserSeparator}") String separator) {
        super(regex, separator);
    }

    /**
     * Parses a line of text into a {@link PlayerData} object.
     *
     * <p>This method first validates the line and then splits it using the specified separator.
     * It extracts the player's login and their associated statistics, which are stored
     * in a {@link HashMap} keyed by {@link LocalDateTime}.</p>
     *
     * @param line the line of text to parse
     * @return a {@link PlayerData} object containing the parsed data
     * @throws IllegalArgumentException if the line is invalid
     */
    @Override
    public PlayerData parse(String line) {
        validateLine(line);

        var statistics = new HashMap<LocalDateTime, Integer>();
        var split = line.split(separator);
        var login = split[0];

        for (int i = 1; i < split.length; i += 2) {
            statistics.put(LocalDateTime.parse(split[i]), Integer.parseInt(split[i + 1]));
        }

        return new PlayerData(login, statistics);
    }
}