package com.georgeracu.web.room;

import com.georgeracu.room.RoomsController;
import com.georgeracu.room.model.Room;
import com.georgeracu.room.usecase.CreateRoomUseCase;
import com.georgeracu.room.usecase.GetRoomsUseCase;
import com.georgeracu.web.PostgresDBInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomsController.class)
@ActiveProfiles("integration-test")
@ContextConfiguration(initializers = PostgresDBInitializer.class)
class RoomsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetRoomsUseCase getRoomsUseCase;
    @MockBean
    private CreateRoomUseCase createRoomUseCase;

    @Test
    void shouldGetAllRooms() throws Exception {
        // arrange
        final Room room = Room.builder().name("Some name").build();
        when(getRoomsUseCase.execute()).thenReturn(Collections.singletonList(room));

        // act
        this.mockMvc.perform(get("/api/v1/rooms"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Some name\"}]"));
    }

    @Test
    void shouldCreateARoom() throws Exception {
        // arrange
        final Room room = Room.builder().name("Some name").build();
        when(createRoomUseCase.execute(any())).thenReturn(room);

        // act
        this.mockMvc.perform(post("/api/v1/rooms"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"name\":\"Some name\"}"));
    }

}
