package com.georgeracu.room.usecase;

import com.georgeracu.persistence.room.RoomsRepository;
import com.georgeracu.room.model.Room;
import com.georgeracu.room.model.RoomEntityToRoom;
import com.georgeracu.room.model.RoomToRoomEntity;

public final class CreateRoomUseCaseImpl implements CreateRoomUseCase {

    private final RoomsRepository roomsRepository;

    public CreateRoomUseCaseImpl(final RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    @Override
    public Room execute(final Room request) {
        return RoomEntityToRoom.map(roomsRepository.save(RoomToRoomEntity.map(request)));
    }
}
