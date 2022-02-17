package com.georgeracu.demo.springboot.port;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresDBInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine3.15")
            .withReuse(true);

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        postgres.start();
        TestPropertyValues.of("spring.datasource.username=" + postgres.getUsername(),
                "spring.datasource.password=" + postgres.getPassword(),
                "spring.datasource.url=" + postgres.getJdbcUrl()).applyTo(context.getEnvironment());
    }
}