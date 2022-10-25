package com.georgeracu.persistence;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = PostgresDBInitializer.class)
class AppTest {
    @Test
    void contextLoads() {
    }
}
