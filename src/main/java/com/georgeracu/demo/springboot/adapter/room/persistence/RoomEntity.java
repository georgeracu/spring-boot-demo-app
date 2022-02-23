package com.georgeracu.demo.springboot.adapter.room.persistence;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Builder
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Entity(name = "RoomEntity")
@Table(name = "rooms")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    public RoomEntity(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

}
