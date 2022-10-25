package com.georgeracu.room.model;


import com.georgeracu.persistence.room.RoomEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class RoomToRoomEntityTest {

    @Test
    void shouldMapAllFields() {
        // arrange
        final Room room = Room.builder().name("Some name").build();
        final RoomEntity expected = RoomEntity.builder().name("Some name").build();

        // act
        final RoomEntity actual = RoomToRoomEntity.map(room);

        // assert
        assertThat(actual).isNotNull()
                .usingRecursiveComparison().isEqualTo(expected);
    }
}