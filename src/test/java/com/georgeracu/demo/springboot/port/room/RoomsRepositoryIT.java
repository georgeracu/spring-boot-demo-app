package com.georgeracu.demo.springboot.port.room;


import com.georgeracu.demo.springboot.adapter.room.persistence.RoomEntity;
import com.georgeracu.demo.springboot.port.PostgresDBInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(initializers = PostgresDBInitializer.class)
class RoomsRepositoryIT {

    @Autowired
    private RoomsRepository roomsRepository;

    @Test
    void shouldConnectToTheDatabase() {
        // arrange
        assertThat(roomsRepository).isNotNull();

        final RoomEntity entity = RoomEntity.builder().build();
        assertThat(entity.getId()).isNull();

        // act
        RoomEntity saved = roomsRepository.save(entity);

        // assert
        assertThat(saved.getId())
                .isNotNull()
                .isInstanceOf(UUID.class);
    }
}