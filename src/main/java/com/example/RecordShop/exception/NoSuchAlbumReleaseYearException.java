package com.example.RecordShop.exception;

public class NoSuchAlbumReleaseYearException extends RuntimeException {
    private String message;

    public NoSuchAlbumReleaseYearException() {
    }

    public NoSuchAlbumReleaseYearException(String msg) {
        super(msg);
        this.message = msg;
    }
}
