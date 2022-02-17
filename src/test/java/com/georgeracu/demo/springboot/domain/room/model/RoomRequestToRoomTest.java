package com.georgeracu.demo.springboot.domain.room.model;

import com.georgeracu.demo.springboot.adapter.room.rest.RoomRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoomRequestToRoomTest {

    @Test
    void shouldMapAllFields() {
        // arrange
        final RoomRequest request = RoomRequest.builder().build();
        final Room expected = Room.builder().build();

        // act
        final Room actual = RoomRequestToRoom.map(request);

        // assert
        assertThat(actual)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(expected);
    }
}