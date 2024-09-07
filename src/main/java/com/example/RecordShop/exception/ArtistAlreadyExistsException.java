package com.example.RecordShop.exception;

public class ArtistAlreadyExistsException extends RuntimeException {
    private String message;

    public ArtistAlreadyExistsException() {}

    public ArtistAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
