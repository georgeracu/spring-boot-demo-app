package com.georgeracu.demo.springboot.domain.room.model;

import com.georgeracu.demo.springboot.adapter.room.rest.RoomRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RoomRequestToRoomTest {

    @Test
    void shouldMapAllFields() {
        // arrange
        final RoomRequest request = RoomRequest.builder().name("Some name").build();
        final Room expected = Room.builder().name("Some name").build();

        // act
        final Room actual = RoomRequestToRoom.map(request);

        // assert
        assertThat(actual)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenNullSource() {
        assertThatThrownBy(() -> RoomRequestToRoom.map(null)).isInstanceOf(NullPointerException.class);
    }
}