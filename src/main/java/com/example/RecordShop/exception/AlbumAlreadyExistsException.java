package com.example.RecordShop.exception;

public class AlbumAlreadyExistsException extends RuntimeException {
    private String message;

    public AlbumAlreadyExistsException() {}

    public AlbumAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
