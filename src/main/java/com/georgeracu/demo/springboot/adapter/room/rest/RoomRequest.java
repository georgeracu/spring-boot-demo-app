package com.georgeracu.demo.springboot.adapter.room.rest;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public final class RoomRequest {

    private String name;
}
