package com.georgeracu.demo.springboot.port.hotel;

import com.georgeracu.demo.springboot.domain.hotel.model.Hotel;

import java.util.List;

public interface GetHotelsUseCase {
    List<Hotel> execute();
}
