package com.hotel.onlinehotelmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.onlinehotelmanagement.exception.UserNotFoundException;
import com.hotel.onlinehotelmanagement.exception.RoomAlreadyExistsException;
import com.hotel.onlinehotelmanagement.exception.InvalidRoomDataException;
import com.hotel.onlinehotelmanagement.model.Employee;
import com.hotel.onlinehotelmanagement.model.Room;
import com.hotel.onlinehotelmanagement.repo.EmployeeRepo;
import com.hotel.onlinehotelmanagement.repo.RoomRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoomService {
    private final RoomRepo roomRepo;
    private final EmployeeRepo employeeRepo;

    @Autowired
    public RoomService(RoomRepo roomRepo, EmployeeRepo employeeRepo) {
        this.roomRepo = roomRepo;
        this.employeeRepo = employeeRepo;
    }

    public Room addRoom(Room room, Long employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new UserNotFoundException("Employee with ID " + employeeId + " not found"));
        room.setEmployee(employee);
        if (room.getRoomNumber() == null) {
            throw new InvalidRoomDataException("Room number must be provided");
        }
        if (roomRepo.findById(room.getRoomNumber()).isPresent()) {
            throw new RoomAlreadyExistsException("Room with room number " + room.getRoomNumber() + " already exists");
        }
        if (room.getRoomCode() == null) {
            room.setRoomCode(generateRoomCode());
        }
        return roomRepo.save(room);
    }

    public List<Room> findAllRooms() {
        return roomRepo.findAll();
    }

    public Room findRoomByRoomNumber(Long roomNumber) {
        return roomRepo.findById(roomNumber)
                .orElseThrow(() -> new UserNotFoundException("Room with room number " + roomNumber + " not found"));
    }

    public Room updateRoomByRoomNumber(Room room, Long roomNumber, Long employeeId) {
        Room existingRoom = roomRepo.findById(roomNumber)
                .orElseThrow(() -> new UserNotFoundException("Room with room number " + roomNumber + " not found"));
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new UserNotFoundException("Employee with ID " + employeeId + " not found"));
        existingRoom.setType(room.getType());
        existingRoom.setPrice(room.getPrice());
        existingRoom.setAvailable(room.getAvailable());
        existingRoom.setImageUrl(room.getImageUrl());
        existingRoom.setEmployee(employee);
        if (existingRoom.getRoomCode() == null) {
            existingRoom.setRoomCode(generateRoomCode());
        }
        return roomRepo.save(existingRoom);
    }

    @Transactional
    public void deleteRoom(Long roomNumber) {
        roomRepo.deleteById(roomNumber);
    }

    private String generateRoomCode() {
        return "RC" + System.currentTimeMillis();
    }
}