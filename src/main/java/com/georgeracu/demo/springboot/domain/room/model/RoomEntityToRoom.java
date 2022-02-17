package com.georgeracu.demo.springboot.domain.room.model;


import com.georgeracu.demo.springboot.adapter.room.persistence.RoomEntity;

public final class RoomEntityToRoom {

    private RoomEntityToRoom() {
        // nothing to see here
    }

    public static Room map(final RoomEntity entity) {
        return Room.builder()
                .name(entity.getName())
                .build();
    }
}
