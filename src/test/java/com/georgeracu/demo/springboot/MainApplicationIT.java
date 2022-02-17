package com.georgeracu.demo.springboot;

import com.georgeracu.demo.springboot.port.PostgresDBInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("integration-test")
@ContextConfiguration(initializers = PostgresDBInitializer.class)
class MainApplicationIT {

	@Test
	void contextLoads() {
	}

}
