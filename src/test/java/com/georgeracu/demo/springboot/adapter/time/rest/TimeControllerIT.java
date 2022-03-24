package com.georgeracu.demo.springboot.adapter.time.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TimeController.class)
@ActiveProfiles("integration-test")
class TimeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnTimeStampWhenGET() throws Exception {
        // act
        this.mockMvc.perform(get("/api/v1/time"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("timeStamp")));
    }

}
