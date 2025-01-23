package com.hotel.onlinehotelmanagement.exception;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(String message) {
    super(message);
}
}
