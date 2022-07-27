package com.georgeracu.demo.springboot.adapter.rest.booking;

import com.georgeracu.demo.springboot.domain.booking.BookingQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/v1/bookings/query")
public class BookingController {

    private final BookingQuery queryExecutor;

    @Autowired
    public BookingController(final BookingQuery queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    @PostMapping
    public Set<BookingResponse> findAllBookings() {
        return queryExecutor.allBooking().stream()
                .map(BookingToBookingResponse::map)
                .collect(Collectors.toSet());
    }
}
