package com.georgeracu.persistence.room;


import com.georgeracu.persistence.PostgresDBInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

    @BeforeEach
    void setup() {
        roomsRepository.deleteAll();
    }

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