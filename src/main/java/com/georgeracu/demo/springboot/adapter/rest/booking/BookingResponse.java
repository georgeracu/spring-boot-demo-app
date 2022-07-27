package com.georgeracu.demo.springboot.adapter.rest.booking;

import com.georgeracu.demo.springboot.adapter.hotel.rest.HotelResponse;
import com.georgeracu.demo.springboot.adapter.room.rest.RoomResponse;
import lombok.Builder;

import java.util.Set;

public record BookingResponse(Set<RoomResponse> rooms, HotelResponse hotel) {
    @Builder public BookingResponse {
    }
}
