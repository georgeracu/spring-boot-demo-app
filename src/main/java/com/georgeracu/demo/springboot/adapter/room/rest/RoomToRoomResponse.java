package com.georgeracu.demo.springboot.adapter.room.rest;


import com.georgeracu.demo.springboot.domain.room.model.Room;

public final class RoomToRoomResponse {

    private RoomToRoomResponse() {
        // nothing to see here
    }

    public static RoomResponse map(final Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        return RoomResponse.builder()
                .name(room.name())
                .build();
    }
}
