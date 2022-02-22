package com.georgeracu.demo.springboot.adapter.room.rest;

import com.georgeracu.demo.springboot.domain.room.model.Room;
import com.georgeracu.demo.springboot.port.room.CreateRoomUseCase;
import com.georgeracu.demo.springboot.port.room.GetRoomsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Author georgicaracu
 */
class RoomsControllerTest {

    private RoomsController controller;
    private GetRoomsUseCase getRoomsUseCase;
    private CreateRoomUseCase createRoomUseCase;

    @BeforeEach
    void setup() {
        getRoomsUseCase = Mockito.mock(GetRoomsUseCase.class);
        createRoomUseCase = Mockito.mock(CreateRoomUseCase.class);

        controller = new RoomsController(getRoomsUseCase, createRoomUseCase);
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

    @Test
    void shouldCreateRoom() {
        // arrange
        final RoomRequest request = RoomRequest.builder().name("Blue room").build();
        final RoomResponse expected = RoomResponse.builder().name("Blue room").build();
        final Room room = Room.builder().name("Blue room").build();
        when(createRoomUseCase.execute(room)).thenReturn(Optional.of(Room.builder().name("Blue room").build()));

        // act
        final ResponseEntity<RoomResponse> response = controller.createRoom(request);

        // assert
        assertThat(response).isNotNull();
        assertThat(response.getBody())
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(expected);

        verify(createRoomUseCase).execute(room);
    }

    @Test
    void shouldNotCreateRoomWhenInvalidPayload() {

        // act
        final ResponseEntity<RoomResponse> response = controller.createRoom(null);

        // assert
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);

        verifyNoInteractions(createRoomUseCase);
    }
}