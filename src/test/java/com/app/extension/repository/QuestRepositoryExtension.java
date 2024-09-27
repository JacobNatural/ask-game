package com.app.extension.repository;

import com.app.line_parser.impl.QuestParser;
import com.app.quest.Quest;
import com.app.repository.impl.QuestRepositoryImpl;
import com.app.txt.load.impl.QuestFileReader;
import com.app.txt.model.QuestData;
import com.app.txt.transfer.impl.TransferImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Field;
import java.nio.file.Paths;

import static com.app.data_provider.DataProvider.*;

public class QuestRepositoryExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(QuestRepositoryImpl.class);
    }

    @Override
    @SneakyThrows
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Field idCounterField = Quest.class.getDeclaredField("idCounter");
        idCounterField.setAccessible(true);
        idCounterField.set(null,1L);
        var transfer = new TransferImpl<QuestData>();
        var parser = new QuestParser(
                "(([A-C];)([\\p{L} \\d]+;)([\",' \\p{L}\\d?-]+;)([ \\p{L}\\d-]+-(true|false);){2}([ \\p{L}\\d-]+-(true|false);))|(0)",
                ";",1);
        var load = new QuestFileReader(transfer, parser);
        var filename = Paths.get(
                QuestRepositoryExtension.class.getClassLoader().getResource(FILENAME_QUESTS).toURI()).toString();
        return new QuestRepositoryImpl(filename,load);
    }
}
