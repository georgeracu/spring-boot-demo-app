package com.georgeracu.demo.springboot.domain.hotel.model;

import lombok.Builder;

@Builder
public class Hotel {
    private final String name;

    public Hotel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
