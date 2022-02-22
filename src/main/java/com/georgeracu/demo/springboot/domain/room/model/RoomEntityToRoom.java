package com.georgeracu.demo.springboot.domain.room.model;


import com.georgeracu.demo.springboot.adapter.room.persistence.RoomEntity;

import java.util.Objects;
import java.util.Optional;

public final class RoomEntityToRoom {

    private RoomEntityToRoom() {
        // nothing to see here
    }

    public static Optional<Room> map(final RoomEntity entity) {
        Objects.requireNonNull(entity, "Source object cannot be null");
        return Optional.of(entity)
                .map(source -> Room.builder()
                           .name(source.getName())
                           .build());
    }
}
