//package com.app.extension.player;
//
//import com.app.txt.model.PlayerData;
//import org.junit.jupiter.api.extension.ExtensionContext;
//import org.junit.jupiter.api.extension.ParameterContext;
//import org.junit.jupiter.api.extension.ParameterResolutionException;
//import org.junit.jupiter.api.extension.ParameterResolver;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.HashMap;
//import java.util.Map;
//
//public class PlayerDataExtension implements ParameterResolver {
//    @Override
//    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
//        return parameterContext.getParameter().getType().equals(PlayerData.class);
//    }
//
//    @Override
//    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
//        return new PlayerData(
//                "Jakub",
//                new HashMap<>(Map.of(
//                        LocalDateTime.of(
//                                LocalDate.of(2024, 4, 29),
//                                LocalTime.of(18, 20,23)
//                        ), 90
//                ))
//        );
//    }
//}
