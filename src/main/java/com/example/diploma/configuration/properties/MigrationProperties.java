package com.example.diploma.configuration.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MigrationProperties {
    @Value("${spring.flyway.migration-enabled}")
    private Boolean migrationEnabled;
}
