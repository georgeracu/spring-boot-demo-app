package com.georgeracu.room.model;


import com.georgeracu.persistence.room.RoomEntity;

import java.util.Optional;

public final class RoomEntityToRoom {

    private RoomEntityToRoom() {
        // nothing to see here
    }

    public static Room map(final RoomEntity entity) {
        return Optional.ofNullable(entity)
                .map(source -> Room.builder()
                        .name(source.getName())
                        .build())
                .orElseThrow(() -> new NullPointerException("Source object cannot be null"));
    }
}
