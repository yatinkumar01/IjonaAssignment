package com.ijona.bookmanagement.Exceptions;

public class EmptyListException extends Exception{

    public EmptyListException() {
    }

    public EmptyListException(String message) {
        super(message);
    }
}
