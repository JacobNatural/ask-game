package com.app.txt.save.impl;

import com.app.player.Player;
import com.app.txt.save.FileWriter;
import com.app.txt.save.generic.AbstractFileWriter;
import com.app.txt.transfer.impl.TransferImpl;
import org.springframework.stereotype.Component;

/**
 * Concrete implementation of a file writer specifically for saving Player data.
 * This class extends the abstract file writer and utilizes the transfer mechanism
 * to handle the actual file operations.
 */
@Component
public class PlayerFileWriter extends AbstractFileWriter<Player> implements FileWriter<Player> {

    /**
     * Constructs a PlayerFileWriter with the specified transfer implementation.
     *
     * @param transfer the TransferImpl instance used for file operations
     */
    public PlayerFileWriter(TransferImpl<Player> transfer) {
        super(transfer);
    }
}