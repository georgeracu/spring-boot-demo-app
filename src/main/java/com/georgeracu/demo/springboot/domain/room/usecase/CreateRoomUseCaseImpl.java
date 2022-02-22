package com.georgeracu.demo.springboot.domain.room.usecase;

import com.georgeracu.demo.springboot.adapter.room.persistence.RoomToRoomEntity;
import com.georgeracu.demo.springboot.domain.room.model.Room;
import com.georgeracu.demo.springboot.domain.room.model.RoomEntityToRoom;
import com.georgeracu.demo.springboot.port.room.CreateRoomUseCase;
import com.georgeracu.demo.springboot.port.room.RoomsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateRoomUseCaseImpl implements CreateRoomUseCase {

    private final RoomsRepository roomsRepository;

    public CreateRoomUseCaseImpl(final RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    @Override
    public Optional<Room> execute(final Room request) {
        return RoomEntityToRoom.map(roomsRepository.save(RoomToRoomEntity.map(request)));
    }
}
