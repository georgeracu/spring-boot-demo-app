package com.georgeracu.demo.springboot.port.room;

import com.georgeracu.demo.springboot.domain.room.model.Room;

public interface CreateRoomUseCase {
    Room execute(final Room room);
}
