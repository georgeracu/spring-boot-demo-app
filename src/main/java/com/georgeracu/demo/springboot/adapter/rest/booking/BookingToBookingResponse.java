package com.georgeracu.demo.springboot.adapter.rest.booking;

import com.georgeracu.demo.springboot.adapter.hotel.rest.HotelToHotelResponse;
import com.georgeracu.demo.springboot.adapter.room.rest.RoomToRoomResponse;
import com.georgeracu.demo.springboot.domain.booking.Booking;

import java.util.stream.Collectors;

public final class BookingToBookingResponse {

    public static BookingResponse map(final Booking source) {
        if (source == null) {
            throw new IllegalArgumentException("Source object cannot be null");
        }

        return BookingResponse.builder()
                .hotel(HotelToHotelResponse.map(source.hotel()))
                .rooms(source.rooms().stream().map(RoomToRoomResponse::map).collect(Collectors.toSet()))
                .build();
    }
}
