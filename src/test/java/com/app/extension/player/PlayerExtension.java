//package com.app.extension.player;
//
//import com.app.model.player.PlayerData;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.api.extension.ParameterContext;
//import org.junit.jupiter.api.extension.ParameterResolutionException;
//import org.junit.jupiter.api.extension.ParameterResolver;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//public class PlayerExtension implements ParameterResolver {
//    @Override
//    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
//        return parameterContext.getParameter().getType().equals(PlayerData.class);
//    }
//
//    @Override
//    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
//        return new PlayerData("Jakub",
//                new HashMap<>(Map.of(
//                        LocalDateTime.parse("2024-04-29T18:20:23")
//                        , 90
//                ))
//        );
//    }
//}
