package com.georgeracu.demo.springboot.adapter.room.rest;

import com.georgeracu.demo.springboot.domain.room.model.Room;
import com.georgeracu.demo.springboot.port.room.GetRoomsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author georgicaracu
 */
class RoomsControllerTest {

    private RoomsController controller;

    private GetRoomsUseCase getRoomsUseCase;

    @BeforeEach
    void setup() {
        getRoomsUseCase = Mockito.mock(GetRoomsUseCase.class);
        controller = new RoomsController(getRoomsUseCase);
    }

    @Test
    void shouldReturnRoomsWhenAvailable() {
        // arrange
        final Room room = Room.builder().name("Some name").build();
        when(getRoomsUseCase.execute()).thenReturn(Collections.singletonList(room));
        final RoomResponse roomResponse = RoomToRoomResponse.map(room);

        // act
        final List<RoomResponse> rooms = controller.getRooms();

        // assert
        assertThat(rooms)
                .isNotNull()
                .isNotEmpty()
                .containsExactly(roomResponse);
    }

    @Test
    void shouldReturnEmptyListWhenNoRoomsAvailable() {
        // arrange
        when(getRoomsUseCase.execute()).thenReturn(Collections.emptyList());

        // act
        final List<RoomResponse> rooms = controller.getRooms();

        // assert
        assertThat(rooms)
                .isNotNull()
                .isEmpty();
    }

}