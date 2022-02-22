package com.georgeracu.demo.springboot.adapter.room.rest;

import lombok.Builder;

public record RoomResponse(String name) {

    @Builder public RoomResponse {}
}
