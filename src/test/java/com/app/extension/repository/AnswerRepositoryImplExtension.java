package com.app.extension.repository;

import com.app.repository.impl.AnswerRepositoryImpl;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class AnswerRepositoryImplExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(AnswerRepositoryImpl.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

        var jdbi = extensionContext
                .getStore(ExtensionContext.Namespace.GLOBAL)
                .get("jdbi", Jdbi.class);

        return new AnswerRepositoryImpl(jdbi);
    }
}
