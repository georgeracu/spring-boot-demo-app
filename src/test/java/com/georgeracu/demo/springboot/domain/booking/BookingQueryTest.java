package com.georgeracu.demo.springboot.domain.booking;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BookingQueryTest {

    private final BookingQuery bookingQuery = new BookingQuery();
    @Test
    void shouldReturnBooking() {
        Set<Booking> bookings = bookingQuery.allBooking();

        assertThat(bookings).isNotNull()
                .isNotEmpty();

        Booking booking = bookings.iterator().next();
        assertThat(booking.hotel()).isNotNull();
        assertThat(booking.rooms())
                .isNotNull()
                .hasSize(2);
    }
}