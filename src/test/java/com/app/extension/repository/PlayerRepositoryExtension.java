package com.app.extension.repository;

import com.app.line_parser.impl.PlayerParser;
import com.app.player.Player;
import com.app.repository.impl.PlayerRepositoryImpl;
import com.app.txt.load.impl.PlayerFileReader;
import com.app.txt.model.PlayerData;
import com.app.txt.transfer.impl.TransferImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Field;
import java.nio.file.Paths;

import static com.app.data_provider.DataProvider.FILENAME_PLAYERS;

public class PlayerRepositoryExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(PlayerRepositoryImpl.class);
    }

    @Override
    @SneakyThrows
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Field idCounterField = Player.class.getDeclaredField("idCounter");
        idCounterField.setAccessible(true);
        idCounterField.set(null,1L);
        var playerParser = new PlayerParser(
                "[a-zA-Z]+;(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2};\\d+;)*(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2};\\d+)",
                ";");
        var transfer = new TransferImpl<PlayerData>();
        var load = new PlayerFileReader(transfer, playerParser);
        var filename = Paths.get(
                PlayerRepositoryExtension.class.getClassLoader().getResource(FILENAME_PLAYERS).toURI()).toString();
        return new PlayerRepositoryImpl(filename, load);
    }
}
