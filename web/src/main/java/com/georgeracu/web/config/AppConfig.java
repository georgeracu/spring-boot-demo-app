package com.georgeracu.web.config;

import com.georgeracu.persistence.room.RoomsRepository;
import com.georgeracu.room.usecase.CreateRoomUseCase;
import com.georgeracu.room.usecase.CreateRoomUseCaseImpl;
import com.georgeracu.room.usecase.GetRoomsUseCase;
import com.georgeracu.room.usecase.GetRoomsUseCaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private RoomsRepository roomsRepository;

    @Bean
    public GetRoomsUseCase getRoomsUseCase() {
        return new GetRoomsUseCaseImpl(roomsRepository);
    }

    @Bean
    public CreateRoomUseCase createRoomUseCase() {
        return new CreateRoomUseCaseImpl(roomsRepository);
    }
}
