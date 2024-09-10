package com.example.RecordShop.controller;

import com.example.RecordShop.model.Album;
import com.example.RecordShop.service.AlbumService;
import com.example.RecordShop.service.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

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

    @GetMapping("/artist/{name}")
    public ResponseEntity<List<Album>> getAlbumsByArtist(@PathVariable String name) {
        List<Album> albums = albumService.findByArtistName(name);
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        Album addAlbum = albumService.addAlbum(album);
        return new ResponseEntity<>(addAlbum, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@RequestBody Album newAlbum, @PathVariable Long id) {
        return new ResponseEntity<>(albumService.updateAlbum(newAlbum, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Album> deleteAlbum(@PathVariable Long id){
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

}
