package com.example.RecordShop.exception;

public class NoSuchAlbumException extends RuntimeException {
    private String message;

    public NoSuchAlbumException() {
    }

    public NoSuchAlbumException(String msg) {
        super(msg);
        this.message = msg;
    }
}
