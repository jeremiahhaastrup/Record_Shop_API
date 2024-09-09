package com.example.RecordShop.controller;

import com.example.RecordShop.model.Album;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        return new ResponseEntity<>(artistService.getAllArtists(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist) {
        Artist addArtist = artistService.addArtist(artist);
        return new ResponseEntity<>(addArtist, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Artist artist = artistService.getArtistById(id);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@RequestBody Artist newArtist, @PathVariable Long id) {
        return new ResponseEntity<>(artistService.updateArtist(newArtist, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Album> deleteArtist(@PathVariable Long id){
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}
