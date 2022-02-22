package com.georgeracu.demo.springboot.port.room;

import com.georgeracu.demo.springboot.domain.room.model.Room;

import java.util.Optional;

public interface CreateRoomUseCase {
    Optional<Room> execute(final Room room);
}
