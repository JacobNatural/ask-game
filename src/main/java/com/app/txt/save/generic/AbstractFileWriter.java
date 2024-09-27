package com.app.txt.save.generic;

import com.app.txt.save.FileWriter;
import com.app.txt.transfer.impl.TransferImpl;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;

/**
 * Abstract base class for file writers, providing common functionality to save data to files.
 *
 * @param <T> the type of data being written to the file
 */
@RequiredArgsConstructor
public abstract class AbstractFileWriter<T> implements FileWriter<T> {

    private final TransferImpl<T> transfer;

    /**
     * Saves a list of data to a specified file using a provided function to format the data.
     *
     * @param filename the name of the file to which data will be saved
     * @param data     the list of data to be saved
     * @param function a function that defines how to convert each item of type T into a String
     */
    @Override
    public void save(String filename, List<T> data, Function<T, String> function) {
        transfer.save(filename, data, function);
    }
}