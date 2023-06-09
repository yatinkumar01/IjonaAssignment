package com.ijona.bookmanagement.Exceptions;

public class AlreadyExistException extends Exception {

    public AlreadyExistException() {
    }

    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(String bookAlreadyExist, String bookIsbn) {
        super(bookAlreadyExist + bookIsbn);
    }
}
