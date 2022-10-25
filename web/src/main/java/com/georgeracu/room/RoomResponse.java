package com.georgeracu.room;

import lombok.Builder;

public record RoomResponse(String name) {

    @Builder public RoomResponse {}
}
