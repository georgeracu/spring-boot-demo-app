package com.georgeracu.hotel.usecase;


import com.georgeracu.hotel.model.Hotel;

import java.util.List;

public interface GetHotelsUseCase {
    List<Hotel> execute();
}
