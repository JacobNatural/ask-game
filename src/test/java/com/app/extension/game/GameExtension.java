//package com.app.extension.txt.game;
//
//import com.app.game.config.AppConfig;
//import com.app.game.service.GameService;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.api.extension.ParameterContext;
//import org.junit.jupiter.api.extension.ParameterResolutionException;
//import org.junit.jupiter.api.extension.ParameterResolver;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class GameExtension implements ParameterResolver {
//    @Override
//    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
//        return parameterContext.getParameter().getType().equals(GameService.class);
//    }
//
//    @Override
//    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
//        var context = new AnnotationConfigApplicationContext(AppConfig.class);
//        return context.getBean("gameService", GameService.class);
//    }
//}
