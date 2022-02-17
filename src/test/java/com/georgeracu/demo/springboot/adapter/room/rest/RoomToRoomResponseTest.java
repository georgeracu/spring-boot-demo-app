package com.georgeracu.demo.springboot.adapter.room.rest;

import com.georgeracu.demo.springboot.domain.room.model.Room;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RoomToRoomResponseTest {

    @Test
    void shouldMapAllFields() {
        // arrange
        final Room room = Room.builder().name("Some room").build();
        final RoomResponse expected = RoomResponse.builder().name("Some room").build();

        // act
        final RoomResponse actual = RoomToRoomResponse.map(room);

        // assert
        assertThat(actual).isNotNull()
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldNotMapForNullSource() {
        assertThatThrownBy(() -> RoomToRoomResponse.map(null)).isInstanceOf(IllegalArgumentException.class);
    }
}