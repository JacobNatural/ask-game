package com.app.extension.txt.model;

import com.app.txt.model.PlayerData;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PlayerDataExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(PlayerData.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var statistic = Map.of(LocalDateTime.parse("2024-01-01T22:00"),60);
        return new PlayerData("Jakub",new HashMap<>(statistic));
    }
}
