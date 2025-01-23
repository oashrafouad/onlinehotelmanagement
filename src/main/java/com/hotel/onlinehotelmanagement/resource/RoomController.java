package com.hotel.onlinehotelmanagement.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hotel.onlinehotelmanagement.model.Room;
import com.hotel.onlinehotelmanagement.service.RoomService;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/add/{employeeId}")
    public ResponseEntity<Room> addRoom(@RequestBody Room room, @PathVariable Long employeeId) {
        Room newRoom = roomService.addRoom(room, employeeId);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.findAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/find/{roomNumber}")
    public ResponseEntity<Room> getRoomByRoomNumber(@PathVariable("roomNumber") Long roomNumber) {
        Room room = roomService.findRoomByRoomNumber(roomNumber);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PutMapping("/update/{roomNumber}/{employeeId}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, @PathVariable Long roomNumber, @PathVariable Long employeeId) {
        Room updatedRoom = roomService.updateRoomByRoomNumber(room, roomNumber, employeeId);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{roomNumber}")
    public ResponseEntity<?> deleteRoom(@PathVariable("roomNumber") Long roomNumber) {
        roomService.deleteRoom(roomNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}