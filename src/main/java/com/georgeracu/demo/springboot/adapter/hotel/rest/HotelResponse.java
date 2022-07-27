package com.georgeracu.demo.springboot.adapter.hotel.rest;

public class HotelResponse {
    private final String name;

    public HotelResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
