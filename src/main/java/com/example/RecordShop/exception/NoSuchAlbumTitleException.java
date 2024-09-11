package com.example.RecordShop.exception;

public class NoSuchAlbumTitleException extends RuntimeException {
    private String message;

    public NoSuchAlbumTitleException() {
    }

    public NoSuchAlbumTitleException(String msg) {
        super(msg);
        this.message = msg;
    }
}
