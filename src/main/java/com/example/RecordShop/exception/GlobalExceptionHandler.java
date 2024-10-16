package com.example.RecordShop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchAlbumException.class})
    public ResponseEntity<Object> handleAlbumNotFoundException(NoSuchAlbumException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({AlbumAlreadyExistsException.class})
    public ResponseEntity<Object> handleAlbumAlreadyExistsException(AlbumAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({ArtistAlreadyExistsException.class})
    public ResponseEntity<Object> handleAlbumAlreadyExistsException(ArtistAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({NoSuchArtistException.class})
    public ResponseEntity<Object> handleAlbumNotFoundException(NoSuchArtistException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({NoSuchAlbumGenreException.class})
    public ResponseEntity<Object> handleAlbumNotFoundException(NoSuchAlbumGenreException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({NoSuchAlbumReleaseYearException.class})
    public ResponseEntity<Object> handleAlbumNotFoundException(NoSuchAlbumReleaseYearException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({NoSuchAlbumTitleException.class})
    public ResponseEntity<Object> handleAlbumNotFoundException(NoSuchAlbumTitleException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({NoSuchAlbumException.class})
    public ResponseEntity<Object> handleFailedImageUploadException(FailedImageUploadException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }

    @ExceptionHandler({NoSuchAlbumException.class})
    public ResponseEntity<Object> handleImageFileEmptyException(ImageFileEmptyException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}