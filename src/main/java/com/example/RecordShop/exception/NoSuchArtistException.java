package com.example.RecordShop.exception;

public class NoSuchArtistException extends RuntimeException {
    private String message;

    public NoSuchArtistException() {
    }

    public NoSuchArtistException(String msg) {
        super(msg);
        this.message = msg;
    }
}
