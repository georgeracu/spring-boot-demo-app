package com.georgeracu.web.room;

import com.georgeracu.room.RoomRequest;
import com.georgeracu.room.RoomRequestToRoom;
import com.georgeracu.room.model.Room;
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