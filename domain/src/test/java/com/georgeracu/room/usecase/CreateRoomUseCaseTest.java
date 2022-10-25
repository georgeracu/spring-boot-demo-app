package com.georgeracu.room.usecase;


import com.georgeracu.persistence.room.RoomEntity;
import com.georgeracu.persistence.room.RoomsRepository;
import com.georgeracu.room.model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateRoomUseCaseTest {

    private RoomsRepository repository;
    private CreateRoomUseCase useCase;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(RoomsRepository.class);
        useCase = new CreateRoomUseCaseImpl(repository);
    }

    @Test
    void shouldCreateARoom() {
        // arrange
        final Room expected = Room.builder().name("Green room").build();
        final Room room = Room.builder().name("Blue room").build();
        final RoomEntity entity = RoomEntity.builder().name("Green room").id(UUID.randomUUID()).build();
        when(repository.save(any())).thenReturn(entity);

        // act
//        Either<Throwable, Room> maybeRoom = useCase.execute(room);
//
//        // assert
//        assertThat(maybeRoom.isRight()).isTrue();
//        assertThat(maybeRoom.isLeft()).isFalse();
//        assertThat(maybeRoom.get())
//                .usingRecursiveComparison().isEqualTo(expected);
//
//        verify(repository).save(any());
    }

    @Test
    void shouldNotCreateRoomForNullRequest() {
        // arrange
        // act
//        Either<Throwable, Room> maybeRoom = useCase.execute(null);
//
//        // assert
//        assertThat(maybeRoom.isLeft()).isTrue();
//        assertThat(maybeRoom.getLeft()).isInstanceOf(Throwable.class);
//
//        verifyNoInteractions(repository);
    }
}