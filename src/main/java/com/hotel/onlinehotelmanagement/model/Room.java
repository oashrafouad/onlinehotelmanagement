package com.hotel.onlinehotelmanagement.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class Room implements Serializable {
    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private Long roomNumber;
    private String type;
    private Double price;
    private Boolean available;
    private String imageUrl;

    @Column(nullable = false, updatable = false)
    private String roomCode;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Room() {}

    public Room(Long roomNumber, String type, Double price, Boolean available, String imageUrl, Employee employee) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.available = available;
        this.imageUrl = imageUrl;
        this.employee = employee;
        this.roomCode = generateRoomCode();
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private String generateRoomCode() {
        return "RC" + System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", imageUrl='" + imageUrl + '\'' +
                ", roomCode='" + roomCode + '\'' +
                ", employee=" + employee +
                '}';
    }
}