package com.georgeracu.demo.springboot.port.room;


import com.georgeracu.demo.springboot.adapter.room.persistence.RoomEntity;
import com.georgeracu.demo.springboot.domain.room.model.Room;
import com.georgeracu.demo.springboot.domain.room.model.RoomEntityToRoom;
import com.georgeracu.demo.springboot.domain.room.usecase.GetRoomsUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class GetRoomsUseCaseTest {

    private GetRoomsUseCase sut;

    private final RoomsRepository repository = Mockito.mock(RoomsRepository.class);

    @BeforeEach
    void setup() {
        sut = new GetRoomsUseCaseImpl(repository);
    }

    @Test
    void shouldReturnRoomsWhenAvailable() {
        // arrange
        final RoomEntity entity = RoomEntity.builder().id(UUID.randomUUID()).build();
        final Room expected = RoomEntityToRoom.map(entity).get();
        when(repository.findAll()).thenReturn(Collections.singletonList(entity));

        // act
        final List<Room> rooms = sut.execute();

        // assert
        assertThat(rooms).isNotNull().isNotEmpty();

        final Room room = rooms.get(0);
        assertThat(room).isNotNull().isEqualTo(expected);
    }

    @Test
    void shouldReturnEmptyListWhenNoRoomsAvailable() {
        // arrange
        when(repository.findAll()).thenReturn(Collections.emptyList());

        // act
        final List<Room> rooms = sut.execute();

        // assert
        assertThat(rooms).isNotNull().isEmpty();
    }
}