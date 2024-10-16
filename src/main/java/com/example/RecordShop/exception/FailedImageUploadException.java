package com.example.RecordShop.exception;

public class FailedImageUploadException extends RuntimeException {
    private String message;

    public FailedImageUploadException() {
    }

    public FailedImageUploadException(String msg) {
        super(msg);
        this.message = msg;
    }
}
