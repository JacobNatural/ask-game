package com.app.extension.txt.load;

import com.app.line_parser.impl.QuestParser;
import com.app.txt.load.impl.QuestFileReader;
import com.app.txt.model.QuestData;
import com.app.txt.transfer.impl.TransferImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class QuestLoadExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(QuestFileReader.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var transfer = new TransferImpl<QuestData>();
//        var questParser = new QuestParser(
//                "(([A-C];)([\\p{L} \\d]+;)([\",' \\p{L}\\d?-]+;)([ \\p{L}\\d-]+-(YES|NO);){2}([ \\p{L}\\d-]+-(YES|NO);))|(0)",
//                ";",1);

        var questParser = new QuestParser("(([A-C];)([\\p{L} \\d]+;)([\",' \\p{L}\\d?-]+;)([ \\p{L}\\d-]+-(true|false);){2}([ \\p{L}\\d-]+-(true|false);))|(0)", ";",1);
        return new QuestFileReader(transfer, questParser);
    }
}
