package com.hotel.onlinehotelmanagement.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.hotel.onlinehotelmanagement.model.Room;

import jakarta.transaction.Transactional;

public interface RoomRepo extends JpaRepository<Room, Long> {
    @Modifying
    @Transactional
    void deleteRoomByRoomNumber(Long roomNumber);

    Optional<Room> findRoomByRoomNumber(Long roomNumber);
}