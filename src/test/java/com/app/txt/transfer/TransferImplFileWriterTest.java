package com.app.txt.transfer;

import com.app.extension.txt.transfer.TransferImplExtension;
import com.app.txt.transfer.impl.TransferImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.data_provider.DataProvider.FILENAME_SAVE;
import static com.app.data_provider.DataProvider.TEXT_SAVE;

@ExtendWith(TransferImplExtension.class)
@RequiredArgsConstructor
public class TransferImplFileWriterTest {
    private final TransferImpl<String> transfer;

    @Test
    @DisplayName("When the filename is null")
    public void test1(){
        Assertions.assertThatThrownBy(() ->
                transfer.save(null, TEXT_SAVE, x -> x))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be null");
    }

    @Test
    @DisplayName("When the filename is empty")
    public void test2(){
        Assertions.assertThatThrownBy(() ->
                        transfer.save("", TEXT_SAVE, x -> x))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be empty");
    }

    @Test
    @DisplayName("When the data is empty")
    public void test3(){
        Assertions.assertThatThrownBy(() ->
                        transfer.save(FILENAME_SAVE, null, x -> x))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Data cannot be null");
    }

    @Test
    @DisplayName("When the data is empty")
    public void test4(){
        Assertions.assertThatThrownBy(() ->
                        transfer.save(FILENAME_SAVE, null, x -> x))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Data cannot be null");
    }

    @Test
    @DisplayName("When the function is empty")
    public void test5(){
        Assertions.assertThatThrownBy(() ->
                        transfer.save(FILENAME_SAVE,   TEXT_SAVE, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ToTxtFormat cannot be null");
    }

    @SneakyThrows
    @Test
    @DisplayName("When saving an data to a TXT file properly")
    public void test6(){
        transfer.save(FILENAME_SAVE, TEXT_SAVE, x -> x);

        try(var lines = Files.lines(Paths.get(FILENAME_SAVE))){
            Assertions.assertThat(lines.toList())
                    .isEqualTo(TEXT_SAVE);
        }
    }

    @Test
    @DisplayName("When the filename is invalid")
    public void test7(){
        Assertions.assertThatThrownBy(() ->
                transfer.save("c://xd?", TEXT_SAVE, x -> x))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Filename is invalid");
    }

    @Test
    @DisplayName("When the data is empty")
    public void test8(){
        Assertions.assertThatThrownBy(() ->
                transfer.save(FILENAME_SAVE, List.of(), x -> x))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Data cannot be empty");

    }

    @AfterAll
    @SneakyThrows
    public static void cleanData(){
        Files.deleteIfExists(Paths.get(FILENAME_SAVE));
    }
}
