package com.georgeracu.messaging.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

public interface QueueListener {
    void handle(@Payload ObjectNode payload, @Headers Map<String, Object> payloadHeaders);
}
