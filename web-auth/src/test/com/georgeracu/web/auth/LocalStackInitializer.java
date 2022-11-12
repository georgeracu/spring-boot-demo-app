package com.georgeracu.web.auth;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

public class LocalStackInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final DockerImageName localstackImage = DockerImageName.parse("localstack/localstack:0.11.3");

    private static final LocalStackContainer localStackContainer = new LocalStackContainer(localstackImage)
//            .withServices(LocalStackContainer.Service.SQS)
            .withReuse(true);

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        localStackContainer.start();
//        TestPropertyValues.of("spring.datasource.username=" + localStackContainer.getUsername(),
//                "spring.datasource.password=" + localStackContainer.getPassword(),
//                "spring.datasource.url=" + localStackContainer.getJdbcUrl()).applyTo(context.getEnvironment());
    }
}
