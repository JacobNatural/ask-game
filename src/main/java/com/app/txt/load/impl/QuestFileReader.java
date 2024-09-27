package com.app.txt.load.impl;

import com.app.line_parser.LineParser;
import com.app.txt.load.generic.AbstractFileReader;
import com.app.txt.load.FileReader;
import com.app.txt.model.QuestData;
import com.app.txt.transfer.Transfer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of the Load interface for loading QuestData entities.
 *
 * <p>This class handles the loading of quest data from a text file,
 * utilizing a specified transfer mechanism and line parser.</p>
 */
@Component
public class QuestFileReader extends AbstractFileReader<QuestData> implements FileReader<QuestData> {

    /**
     * Constructs a QuestLoad instance with the specified transfer and parser.
     *
     * @param transfer the data transfer mechanism
     * @param parser   the line parser for QuestData
     */
    public QuestFileReader(Transfer<QuestData> transfer, LineParser<QuestData> parser) {
        super(transfer, parser);
    }

    /**
     * Loads QuestData from the specified file.
     *
     * <p>This method uses the transfer mechanism to read data from the file
     * and parses each line into QuestData objects. Duplicate questions are handled
     * by keeping the first occurrence.</p>
     *
     * @param filename the name of the file to load data from
     * @return a list of QuestData objects
     */
    @Override
    public List<QuestData> load(String filename) {
        return transfer
                .load(filename, parser)
                .stream()
                .collect(Collectors.toMap(
                        QuestData::question,
                        Function.identity(),
                        (v1, v2) -> v1))
                .values()
                .stream()
                .toList();
    }
}