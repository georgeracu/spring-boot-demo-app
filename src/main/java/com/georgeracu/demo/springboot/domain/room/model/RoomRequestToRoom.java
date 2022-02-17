package com.georgeracu.demo.springboot.domain.room.model;


import com.georgeracu.demo.springboot.adapter.room.rest.RoomRequest;

public final class RoomRequestToRoom {

    public static Room map(final RoomRequest request) {
        return Room.builder().build();
    }
}
