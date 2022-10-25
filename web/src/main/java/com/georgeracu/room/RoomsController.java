package com.georgeracu.room;

import com.georgeracu.room.usecase.CreateRoomUseCase;
import com.georgeracu.room.usecase.GetRoomsUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * Author georgicaracu
 */
@Controller
@RequestMapping("/api/v1/rooms")
public class RoomsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomsController.class.getCanonicalName());

    private final GetRoomsUseCase getRoomsUseCase;
    private final CreateRoomUseCase createRoomUseCase;

    @Autowired
    public RoomsController(final GetRoomsUseCase getRoomsUseCase,
                           final CreateRoomUseCase createRoomUseCase) {
        this.getRoomsUseCase = getRoomsUseCase;
        this.createRoomUseCase = createRoomUseCase;
    }

    @GetMapping
    @ResponseBody
    public List<RoomResponse> getRooms() {
        return getRoomsUseCase.execute().stream().map(RoomToRoomResponse::map).toList();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<RoomResponse> createRoom(@Validated RoomRequest request) {
        return Optional.ofNullable(request)
                .map(RoomRequestToRoom::map)
                .map(createRoomUseCase::execute)
                .map(RoomToRoomResponse::map)
                .map(response -> new ResponseEntity<>(response, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

}
