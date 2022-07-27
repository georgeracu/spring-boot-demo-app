package com.georgeracu.demo.springboot.adapter.hotel.rest;

import com.georgeracu.demo.springboot.domain.hotel.model.Hotel;

public final class HotelToHotelResponse {

    public static HotelResponse map(final Hotel source) {
        if (source == null) {
            throw new IllegalArgumentException("Source cannot be null");
        }

        return new HotelResponse(source.getName());
    }
}
