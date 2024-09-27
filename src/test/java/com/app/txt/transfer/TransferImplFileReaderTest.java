package com.app.txt.transfer;

import com.app.extension.txt.transfer.TransferImplExtension;
import com.app.txt.transfer.impl.TransferImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.nio.file.Paths;
import java.util.List;

import static com.app.data_provider.DataProvider.*;

@ExtendWith(TransferImplExtension.class)
@RequiredArgsConstructor
public class TransferImplFileReaderTest {

    private final TransferImpl<String> transfer;
    static private String filename;

    @BeforeAll
    @SneakyThrows
    public static void setUp() {
        filename = Paths.get(
               TransferImpl.class.getClassLoader().getResource(FILENAME_TRANSFER_LOAD).toURI()).toString();
    }

    @Test
    @DisplayName("When the filename is null")
    public void test1() {
        Assertions.assertThatThrownBy(() -> transfer.load(null, x -> x))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be null");
    }

    @Test
    @DisplayName("When the filename is empty")
    public void test2() {
        Assertions.assertThatThrownBy(() -> transfer.load("", x -> x))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Filename cannot be empty");
    }

    @Test
    @DisplayName("When parser is null")
    public void test3(){
        Assertions.assertThatThrownBy(
                () -> transfer.load(filename,null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Parser cannot be null");
    }

    @Test
    @DisplayName("When the filename is not correct")
    public void test4() {
        Assertions.assertThatThrownBy(() ->
                        transfer.load("wrong_file_name.txt", x -> x))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("java.nio.file.NoSuchFileException: wrong_file_name.txt");
    }

    @Test
    @DisplayName("When the transfer read data correctly")
    public void test5(){
        Assertions.assertThat(
                transfer.load(filename, x -> x))
                .isEqualTo(List.of("one", "two", "three"));
    }
}
