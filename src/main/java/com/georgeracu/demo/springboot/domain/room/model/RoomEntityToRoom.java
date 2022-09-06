package com.georgeracu.demo.springboot.domain.room.model;


import com.georgeracu.demo.springboot.adapter.room.persistence.RoomEntity;

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
