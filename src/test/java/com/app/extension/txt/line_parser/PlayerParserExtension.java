package com.app.extension.txt.line_parser;

import com.app.line_parser.impl.PlayerParser;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class PlayerParserExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(PlayerParser.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new PlayerParser("[a-zA-Z]+;(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2};\\d+;)*(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2};\\d+)",";");
    }
}
