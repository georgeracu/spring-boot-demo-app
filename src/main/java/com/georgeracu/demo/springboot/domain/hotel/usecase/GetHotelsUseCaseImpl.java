package com.georgeracu.demo.springboot.domain.hotel.usecase;

import com.georgeracu.demo.springboot.domain.hotel.model.Hotel;
import com.georgeracu.demo.springboot.port.hotel.GetHotelsUseCase;

import java.util.List;

public final class GetHotelsUseCaseImpl implements GetHotelsUseCase {
    @Override
    public List<Hotel> execute() {
        throw new RuntimeException("Not implemented");
    }
}
