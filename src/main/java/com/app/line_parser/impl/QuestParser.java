package com.app.line_parser.impl;

import com.app.difficulty_level.DifficultyLevel;
import com.app.line_parser.LineParser;
import com.app.line_parser.impl.generic.LineParserAbstract;
import com.app.txt.model.QuestData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

/**
 * Concrete implementation of a line parser for parsing quest data.
 *
 * <p>This class extends {@link LineParserAbstract} and implements the {@link LineParser}
 * interface to convert a line of text into a {@link QuestData} object.</p>
 */
@Component
public class QuestParser extends LineParserAbstract<QuestData> implements LineParser<QuestData> {
    private final int minCorrectAnswer;

    /**
     * Constructs a new QuestParser with the specified regex, separator, and minimum correct answers.
     *
     * @param regex            the regular expression used for validating quest data lines
     * @param separator        the separator used in the quest data line
     * @param minCorrectAnswer the minimum number of correct answers allowed for a quest
     */
    public QuestParser(
            @Value("${questRegex}") String regex,
            @Value("${questParserSeparator}") String separator,
            @Value("${minCorrectAnswer}") int minCorrectAnswer) {
        super(regex, separator);
        this.minCorrectAnswer = minCorrectAnswer;
    }

    /**
     * Parses a line of text into a {@link QuestData} object.
     *
     * <p>This method first validates the line and then splits it using the specified separator.
     * It extracts the difficulty level, question, and associated answers, which are stored
     * in a {@link LinkedHashMap} keyed by answer text with boolean values indicating correctness.</p>
     *
     * <p>Additionally, it checks that the number of correct answers falls within the allowed range.</p>
     *
     * @param line the line of text to parse
     * @return a {@link QuestData} object containing the parsed data
     * @throws IllegalArgumentException if the line is invalid or if the number of correct answers is incorrect
     */
    @Override
    public QuestData parse(String line) {
        validateLine(line);
        var mp = new LinkedHashMap<String, Boolean>();
        var split = line.substring(0, line.length() - 1).split(";");
        var counterYes = 0;

        for (int i = 3; i < split.length; i++) {
            var nextSplit = split[i].split("-");
            var confirm = nextSplit[1];
            if (confirm.equals("true")) {
                mp.put(nextSplit[0], true);
                counterYes++;
            } else {
                mp.put(nextSplit[0], false);
            }
        }

        if (counterYes > minCorrectAnswer || counterYes < 1) {
            throw new IllegalArgumentException("Amount of correct answer is incorrect");
        }

        return new QuestData(DifficultyLevel.valueOf(split[0]), split[1], split[2], mp);
    }
}