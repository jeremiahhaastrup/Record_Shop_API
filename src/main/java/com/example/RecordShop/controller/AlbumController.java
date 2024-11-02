package com.example.RecordShop.controller;

import com.example.RecordShop.exception.NoSuchAlbumGenreException;
import com.example.RecordShop.exception.NoSuchAlbumReleaseYearException;
import com.example.RecordShop.exception.NoSuchAlbumTitleException;
import com.example.RecordShop.exception.NoSuchArtistException;
import com.example.RecordShop.model.Album;
import com.example.RecordShop.service.AlbumService;
import com.example.RecordShop.service.CloudinaryService;
import com.example.RecordShop.type.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        return new ResponseEntity<>(albumService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Album>> getAllAlbumsInStock() {
        return new ResponseEntity<>(albumService.getAllAlbumsInStock(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        Album album = albumService.getAlbumById(id);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @GetMapping("/artist")
    public ResponseEntity<List<Album>> getAlbumsByArtist(@RequestParam(value = "name") String name) {
        List<Album> albums = albumService.findByArtistNameContainingIgnoreCase(name);
        if (albums != null) {
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } else {
            throw new NoSuchArtistException(String.format("'%s' does not exist!👎🏽", name));
        }
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Album>> getAlbumsByGenre(@RequestParam(value="name") Genre genre) {
        List<Album> albums = albumService.findByAlbumsGenre(genre);
        if (albums != null) {
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } else {
            throw new NoSuchAlbumGenreException(String.format("'%s' does not exist!👎🏽", genre));
        }
    }

    @GetMapping("/released")
    public ResponseEntity<List<Album>> getAllAlbumsByReleaseYear(@RequestParam(value="year") int year) {
        List<Album> albums = albumService.findByReleaseYear(year);
        if (albums != null) {
            return new ResponseEntity<>(albums, HttpStatus.OK);
        } else {
            throw new NoSuchAlbumReleaseYearException(String.format("'%s' does not exist!👎🏽", year));
        }
    }

    @GetMapping("/title")
    public ResponseEntity <List<Album>> getAllAlbumByTitle(@RequestParam(value="name") String title) {
        List<Album> album = albumService.findByTitle(title);
        if (album != null) {
            return new ResponseEntity<>(album, HttpStatus.OK);
        } else {
            throw new NoSuchAlbumTitleException(String.format("'%s' does not exist!👎🏽", title));
        }
    }

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestPart("album") Album album, @RequestParam("file") MultipartFile file) throws IOException {
        String imageUrl = cloudinaryService.uploadImage(file);
        album.setImageUrl(imageUrl);
        Album addAlbum = albumService.addAlbum(album);
        return new ResponseEntity<>(addAlbum, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@RequestPart("album") Album newAlbum,
                                             @RequestParam(value = "file", required = false) MultipartFile file,
                                             @PathVariable Long id) throws IOException {
        if (file != null && !file.isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(file);
            newAlbum.setImageUrl(imageUrl);
        } else {
            Album existingAlbum = albumService.getAlbumById(id);
            newAlbum.setImageUrl(existingAlbum.getImageUrl());
        }
        return new ResponseEntity<>(albumService.updateAlbum(newAlbum, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Album> deleteAlbum(@PathVariable Long id){
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

}
