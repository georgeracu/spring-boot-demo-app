package com.georgeracu.room.model;

import lombok.Builder;

public record Room(String name) {
    @Builder public Room{}
}
