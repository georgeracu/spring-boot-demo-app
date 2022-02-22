package com.georgeracu.demo.springboot.domain.room.model;

import lombok.Builder;

public record Room(String name) {
    @Builder public Room{}
}
