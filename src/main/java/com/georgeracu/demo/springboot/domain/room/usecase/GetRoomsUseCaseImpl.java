package com.georgeracu.demo.springboot.domain.room.usecase;

import com.georgeracu.demo.springboot.domain.room.model.Room;
import com.georgeracu.demo.springboot.domain.room.model.RoomEntityToRoom;
import com.georgeracu.demo.springboot.port.room.GetRoomsUseCase;
import com.georgeracu.demo.springboot.port.room.RoomsRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRoomsUseCaseImpl implements GetRoomsUseCase {

    private final RoomsRepository roomsRepository;

    public GetRoomsUseCaseImpl(final RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    @Override
    public List<Room> execute() {
        return roomsRepository.findAll().stream().map(RoomEntityToRoom::map).toList();
    }
}
