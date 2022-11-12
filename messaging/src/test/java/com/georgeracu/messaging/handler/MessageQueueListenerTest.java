package com.georgeracu.messaging.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.georgeracu.persistence.room.RoomDetails;
import com.georgeracu.persistence.room.RoomDetailsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

class MessageQueueListenerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RoomDetailsRepository roomDetailsRepository = Mockito.mock(RoomDetailsRepository.class);
    private final MessageQueueListener messageQueueListener = new MessageQueueListener(roomDetailsRepository, objectMapper);

    @Test
    void shouldHandleMessage() {
        // arrange
        var payload = new RoomDetailsPayload(UUID.randomUUID(), "test message");
        var expected = new RoomDetails(payload.id(), payload.name());
        ObjectNode message = objectMapper.valueToTree(payload);
        ArgumentCaptor<RoomDetails> argumentCaptor = ArgumentCaptor.forClass(RoomDetails.class);
        doNothing().when(roomDetailsRepository).save(argumentCaptor.capture());

        // act
        messageQueueListener.handle(message, null);

        // assert
        RoomDetails roomDetails = argumentCaptor.getValue();
        assertThat(roomDetails).isEqualTo(expected);
    }
}
