package com.app.extension.txt.line_parser;

import com.app.line_parser.impl.QuestParser;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class QuestParserExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(QuestParser.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new QuestParser("(([A-C];)([\\p{L} \\d]+;)([\",' \\p{L}\\d?-]+;)([ \\p{L}\\d-]+-(true|false);){2}([ \\p{L}\\d-]+-(true|false);))|(0)",
                ";",1);
    }
}
