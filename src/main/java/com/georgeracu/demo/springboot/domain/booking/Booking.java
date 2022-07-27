package com.georgeracu.demo.springboot.domain.booking;

import com.georgeracu.demo.springboot.domain.hotel.model.Hotel;
import com.georgeracu.demo.springboot.domain.room.model.Room;
import lombok.Builder;

import java.util.Set;

public record Booking(Set<Room> rooms, Hotel hotel) {
    @Builder public Booking {}
}
