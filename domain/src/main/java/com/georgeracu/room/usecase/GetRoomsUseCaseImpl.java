package com.georgeracu.room.usecase;

import com.georgeracu.persistence.room.RoomsRepository;
import com.georgeracu.room.model.Room;
import com.georgeracu.room.model.RoomEntityToRoom;

import java.util.List;

public final class GetRoomsUseCaseImpl implements GetRoomsUseCase {

    private final RoomsRepository roomsRepository;

    public GetRoomsUseCaseImpl(final RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    @Override
    public List<Room> execute() {
        return roomsRepository.findAll().stream()
                .map(RoomEntityToRoom::map)
                .toList();
    }
}
