package com.georgeracu.room;


import com.georgeracu.room.model.Room;

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
