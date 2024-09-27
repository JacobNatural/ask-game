package com.app.txt.transfer.impl;

import com.app.line_parser.LineParser;
import com.app.txt.transfer.Transfer;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of the Transfer interface for loading and saving data.
 *
 * @param <T> the type of data to be transferred
 */
@NoArgsConstructor
@Component
public class TransferImpl<T> implements Transfer<T> {

    /**
     * Loads data from a specified file using the provided line parser.
     *
     * @param filename the name of the file to load data from
     * @param parser   the line parser used to parse each line into an object of type T
     * @return a list of parsed data items of type T
     * @throws IllegalArgumentException if the filename or parser is null or if the filename is empty
     * @throws IllegalStateException    if an error occurs during file reading
     */
    public List<T> load(String filename, LineParser<T> parser) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        if (filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be empty");
        }

        if (parser == null) {
            throw new IllegalArgumentException("Parser cannot be null");
        }

        try (var lines = Files.lines(Paths.get(filename))) {
            return lines
                    .map(parser::parse)
                    .toList();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Saves a list of data items to a specified file using a formatting function.
     *
     * @param filename     the name of the file to save data to
     * @param data         the list of data items to be saved
     * @param toTxtFormat  a function that converts each data item of type T to a String
     * @throws IllegalArgumentException if the filename, data, or formatting function is null or if the filename is empty
     * @throws IllegalStateException    if an error occurs during file writing
     */
    public void save(String filename, List<T> data, Function<T, String> toTxtFormat) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        if (filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be empty");
        }

        if(data == null){
            throw new IllegalArgumentException("Data cannot be null");
        }

        if(data.isEmpty()){
            throw new IllegalArgumentException("Data cannot be empty");
        }

        if(toTxtFormat == null){
            throw new IllegalArgumentException("ToTxtFormat cannot be null");
        }

        try (var fw = new FileWriter(filename, true); var pw = new PrintWriter(fw)) {
            pw.println(data
                    .stream()
                    .map(toTxtFormat)
                    .collect(Collectors.joining("\n")));

        } catch (Exception e) {
            throw new IllegalStateException("Filename is invalid");
        }
    }
}