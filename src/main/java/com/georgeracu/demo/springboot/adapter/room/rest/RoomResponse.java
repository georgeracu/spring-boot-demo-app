package com.georgeracu.demo.springboot.adapter.room.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public final class RoomResponse {

    private String name;

    public RoomResponse(String name) {
        this.name = name;
    }
}
