package com.georgeracu.messaging.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record RoomDetailsPayload(@JsonProperty("id") UUID id,
                                 @JsonProperty("name") String name) {}
