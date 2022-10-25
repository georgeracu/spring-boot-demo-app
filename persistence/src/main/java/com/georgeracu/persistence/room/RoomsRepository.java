package com.georgeracu.persistence.room;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomsRepository extends JpaRepository<RoomEntity, UUID> {
}
