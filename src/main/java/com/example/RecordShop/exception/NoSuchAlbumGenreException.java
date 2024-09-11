package com.example.RecordShop.exception;

public class NoSuchAlbumGenreException extends RuntimeException {
    private String message;

    public NoSuchAlbumGenreException() {
    }

    public NoSuchAlbumGenreException(String msg) {
        super(msg);
        this.message = msg;
    }
}
