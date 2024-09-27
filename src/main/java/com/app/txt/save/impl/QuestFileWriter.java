package com.app.txt.save.impl;

import com.app.quest.Quest;
import com.app.txt.save.FileWriter;
import com.app.txt.save.generic.AbstractFileWriter;
import com.app.txt.transfer.impl.TransferImpl;
import org.springframework.stereotype.Component;

/**
 * Concrete implementation of a file writer specifically for saving Quest data.
 * This class extends the abstract file writer and utilizes the transfer mechanism
 * to handle the actual file operations.
 */
@Component
public class QuestFileWriter extends AbstractFileWriter<Quest> implements FileWriter<Quest> {

    /**
     * Constructs a QuestFileWriter with the specified transfer implementation.
     *
     * @param transfer the TransferImpl instance used for file operations
     */
    public QuestFileWriter(TransferImpl<Quest> transfer) {
        super(transfer);
    }
}