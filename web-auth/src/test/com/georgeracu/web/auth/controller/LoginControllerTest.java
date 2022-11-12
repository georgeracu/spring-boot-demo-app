package com.georgeracu.web.auth.controller;

import com.georgeracu.web.auth.LocalStackInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ContextConfiguration(initializers = LocalStackInitializer.class)
class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void anonymousUsersShouldNotGetSecretMessage() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("secretMessage"))
                .andExpect(model().attribute("message", "AWS Cognito with Spring Security"));
    }
}