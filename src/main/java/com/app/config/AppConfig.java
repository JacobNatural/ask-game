package com.app.config;

import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Configuration class for setting up the application context and managing beans.
 * It loads properties from the application.properties file and initializes required components.
 */
@Configuration
@ComponentScan(basePackages = "com.app")
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class AppConfig {

    /**
     * Environment object used to access properties from the application.properties file.
     */
    private final Environment env;

    /**
     * Creates and configures a Jdbi instance for database interactions using the provided
     * database connection properties from the environment.
     *
     * @return a configured {@link Jdbi} instance.
     */
    @Bean
    public Jdbi jdbi() {

        // Retrieve the database connection properties from the environment
        var URL = env.getProperty("db.url");
        var username = env.getProperty("db.username");
        var password = env.getProperty("db.password");

        // Create and return the Jdbi instance with the provided URL, username, and password
        return Jdbi.create(URL, username, password);
    }
}