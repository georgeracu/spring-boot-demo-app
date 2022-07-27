package com.georgeracu.demo.springboot.domain.booking;

import com.georgeracu.demo.springboot.domain.hotel.model.Hotel;
import com.georgeracu.demo.springboot.domain.room.model.Room;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BookingQuery {
    private final Set<Room> rooms = Set.of(Room.builder().name("Room 1").build(), Room.builder().name("Room 2").build());
    private final Hotel hotel = Hotel.builder().name("Awesome Hotel").build();

    @QueryMapping
    public Set<Booking> allBooking() {
        return Set.of(Booking.builder().hotel(hotel).rooms(rooms).build());
    }
}
