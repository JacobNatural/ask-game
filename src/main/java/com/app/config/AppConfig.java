package com.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration class for the application.
 *
 * <p>This class is responsible for defining the base configuration of the application. It uses
 * Spring's {@link Configuration} annotation to mark it as a source of bean definitions and
 * {@link ComponentScan} to scan for Spring components in the specified base package.
 *
 * <p>The {@link PropertySource} annotation is used to load properties from the
 * <code>application.properties</code> file located in the classpath.
 */
@Configuration
@ComponentScan(basePackages = "com.app")
@PropertySource("classpath:application.properties")
public class AppConfig {
}