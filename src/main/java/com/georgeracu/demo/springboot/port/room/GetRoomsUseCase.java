package com.georgeracu.demo.springboot.port.room;


import com.georgeracu.demo.springboot.domain.room.model.Room;

import java.util.List;

public interface GetRoomsUseCase {
    List<Room> execute();
}
