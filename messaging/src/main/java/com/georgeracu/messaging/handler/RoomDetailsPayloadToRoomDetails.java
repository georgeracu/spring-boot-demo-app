package com.georgeracu.messaging.handler;

import com.georgeracu.persistence.room.RoomDetails;

public class RoomDetailsPayloadToRoomDetails {

    private RoomDetailsPayloadToRoomDetails() {
    }

    public static RoomDetails map(final RoomDetailsPayload payload) {
        return new RoomDetails(payload.id(), payload.name());
    }
}
