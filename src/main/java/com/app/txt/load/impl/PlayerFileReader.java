package com.app.txt.load.impl;

import com.app.line_parser.LineParser;
import com.app.txt.load.generic.AbstractFileReader;
import com.app.txt.load.FileReader;
import com.app.txt.model.PlayerData;
import com.app.txt.transfer.Transfer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of the Load interface for loading PlayerData entities.
 *
 * <p>This class handles the loading of player data from a text file,
 * utilizing a specified transfer mechanism and line parser.</p>
 */
@Component
public class PlayerFileReader extends AbstractFileReader<PlayerData> implements FileReader<PlayerData> {

    /**
     * Constructs a PlayerLoad instance with the specified transfer and parser.
     *
     * @param transfer the data transfer mechanism
     * @param parser   the line parser for PlayerData
     */
    public PlayerFileReader(Transfer<PlayerData> transfer, LineParser<PlayerData> parser) {
        super(transfer, parser);
    }

    /**
     * Loads PlayerData from the specified file.
     *
     * <p>This method uses the transfer mechanism to read data from the file
     * and parses each line into PlayerData objects. Duplicate logins are merged
     * by updating their statistics.</p>
     *
     * @param filename the name of the file to load data from
     * @return a list of PlayerData objects
     */
    @Override
    public List<PlayerData> load(String filename) {
        return transfer
                .load(filename, parser)
                .stream()
                .collect(Collectors.toMap(
                        PlayerData::login,
                        Function.identity(),
                        (v1, v2) -> {
                            v1.updateStatistic(v2.statistics());
                            return v1;
                        }))
                .values()
                .stream()
                .toList();
    }
}