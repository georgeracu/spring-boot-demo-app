package com.georgeracu.messaging.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.georgeracu.persistence.room.RoomDetailsRepository;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

public class MessageQueueListener implements QueueListener {

    private final RoomDetailsRepository roomDetailsRepository;
    private final ObjectMapper objectMapper;

    public MessageQueueListener(final RoomDetailsRepository roomDetailsRepository, final ObjectMapper objectMapper) {
        this.roomDetailsRepository = roomDetailsRepository;
        this.objectMapper = objectMapper;
    }

    @SqsListener(value = "${cloud.aws.queue.rooms}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    @Override
    public void handle(@Payload ObjectNode payload, @Headers Map<String, Object> payloadHeaders) {
        System.out.println("Message received: ");
        try {
            RoomDetailsPayload roomDetailsPayload = objectMapper.readValue(payload.toString(), RoomDetailsPayload.class);
            roomDetailsRepository.save(RoomDetailsPayloadToRoomDetails.map(roomDetailsPayload));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
