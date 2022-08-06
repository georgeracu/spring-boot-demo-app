package com.georgeracu.demo.springboot.adapter.room.rest;

import com.georgeracu.demo.springboot.domain.room.model.Room;
import com.georgeracu.demo.springboot.domain.room.usecase.CreateRoomUseCaseImpl;
import com.georgeracu.demo.springboot.domain.room.usecase.GetRoomsUseCaseImpl;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomsController.class)
@ActiveProfiles("integration-test")
class RoomsControllerIT {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public MeterRegistry meterRegistry() {
            return new SimpleMeterRegistry();
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MeterRegistry meterRegistry;

    @MockBean
    private GetRoomsUseCaseImpl getRoomsUseCase;
    @MockBean
    private CreateRoomUseCaseImpl createRoomUseCase;

    @BeforeEach
    void setup() {
        meterRegistry.clear();
    }

    @Test
    void shouldGetAllRooms() throws Exception {
        long count = meterRegistry.getMeters().stream().count();
        assertThat(count).isZero();

        // arrange
        final Room room = Room.builder().name("Some name").build();
        when(getRoomsUseCase.execute()).thenReturn(Collections.singletonList(room));

        // act
        this.mockMvc.perform(get("/api/v1/rooms"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Some name\"}]"));

        long newCount = meterRegistry.getMeters().stream()
                .map(m -> m.getId().getName())
                .filter(name -> name.contains("abc"))
                .count();
        assertThat(newCount).isPositive();

    }

    @Test
    void shouldCreateARoom() throws Exception {
        // arrange
        final Room room = Room.builder().name("Some name").build();
        when(createRoomUseCase.execute(any())).thenReturn(Optional.of(room));

        // act
        this.mockMvc.perform(post("/api/v1/rooms"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"name\":\"Some name\"}"));
    }

}
