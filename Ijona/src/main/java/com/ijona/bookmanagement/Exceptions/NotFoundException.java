package com.ijona.bookmanagement.Exceptions;

public class NotFoundException extends Exception {

    public NotFoundException(String message,Integer bookID) {
        super(message + bookID);
    }

    public NotFoundException(String message, String fieldName) {
        super(message + fieldName);
    }
}
