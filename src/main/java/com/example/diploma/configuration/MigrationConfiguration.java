package com.example.diploma.configuration;


import com.example.diploma.configuration.properties.MigrationProperties;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class MigrationConfiguration {
    @Bean
    FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway, null);
    }

    // To avoid circular dependency https://stackoverflow.com/a/68635794/4894316
    static class Dummy {
    }

    @Bean
    @DependsOn("entityManagerFactory")
    Dummy delayedFlywayInitializer(Flyway flyway, MigrationProperties migrationProperties) {
        if (migrationProperties.getMigrationEnabled()) {
            flyway.migrate();
        }
        return new Dummy();
    }
}

