package com.hotel.onlinehotelmanagement.exception;

public class RoomAlreadyExistsException extends RuntimeException{
  public RoomAlreadyExistsException(String message) {
    super(message);
}
}
