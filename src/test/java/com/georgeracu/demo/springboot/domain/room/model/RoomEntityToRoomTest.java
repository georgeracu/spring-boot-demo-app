package com.georgeracu.demo.springboot.domain.room.model;

import com.georgeracu.demo.springboot.adapter.room.persistence.RoomEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RoomEntityToRoomTest {

    @Test
    void shouldMapAllFields() {
        // arrange
        final RoomEntity entity = RoomEntity.builder()
                .id(UUID.randomUUID())
                .name("Some name")
                .build();
        final Room expected = Room.builder()
                .name("Some name")
                .build();
        // act
        final Room actual = RoomEntityToRoom.map(entity);

        // assert
        assertThat(actual)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldThrowExceptionWhenNullSourceObject() {
        assertThatThrownBy(() -> RoomEntityToRoom.map(null)).isInstanceOf(NullPointerException.class);
    }
}