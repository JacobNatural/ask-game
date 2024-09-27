package com.app.extension.txt.load;

import com.app.line_parser.impl.PlayerParser;
import com.app.txt.load.impl.PlayerFileReader;
import com.app.txt.model.PlayerData;
import com.app.txt.transfer.impl.TransferImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class PlayerLoadExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(PlayerFileReader.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var transfer = new TransferImpl<PlayerData>();
        var playerParser = new PlayerParser(
                "[a-zA-Z]+;(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2};\\d+;)*(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2};\\d+)",
                ";");
 //       var playerParser = new PlayerParser(".*",";");
        return new PlayerFileReader(transfer, playerParser);
    }
}
