package com.georgeracu.demo.springboot.domain.room.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public final class Room {
    private String name;
}
