package com.georgeracu.demo.springboot.adapter.room.persistence;


import com.georgeracu.demo.springboot.domain.room.model.Room;

public final class RoomToRoomEntity {

    private RoomToRoomEntity() {
        // nothing here
    }

    public static RoomEntity map(final Room room) {
        return RoomEntity.builder()
                .name(room.getName())
                .build();
    }
}
