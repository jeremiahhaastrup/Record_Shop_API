package com.example.RecordShop.exception;

public class ImageFileEmptyException extends RuntimeException {
    private String message;

    public ImageFileEmptyException() {
    }

    public ImageFileEmptyException(String msg) {
        super(msg);
        this.message = msg;
    }
}
