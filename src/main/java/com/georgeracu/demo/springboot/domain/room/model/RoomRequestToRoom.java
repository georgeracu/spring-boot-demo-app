package com.georgeracu.demo.springboot.domain.room.model;


import com.georgeracu.demo.springboot.adapter.room.rest.RoomRequest;

import java.util.Objects;

public final class RoomRequestToRoom {

    private RoomRequestToRoom() {
        // nothing to see here
    }

    public static Room map(final RoomRequest request) {
        Objects.requireNonNull(request, "Room Request cannot be null");
        return Room.builder()
                .name(request.getName())
                .build();
    }
}
