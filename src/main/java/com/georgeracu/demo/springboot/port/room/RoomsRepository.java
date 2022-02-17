package com.georgeracu.demo.springboot.port.room;


import com.georgeracu.demo.springboot.adapter.room.persistence.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomsRepository extends JpaRepository<RoomEntity, UUID> {
}
