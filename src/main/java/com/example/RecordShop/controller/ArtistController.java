package com.example.RecordShop.controller;

import com.example.RecordShop.model.Album;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.service.ArtistService;
import com.example.RecordShop.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {

    @Autowired
    ArtistService artistService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtists() {
        return new ResponseEntity<>(artistService.getAllArtists(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Artist> addArtist(@RequestPart("artist") Artist artist, @RequestParam("file") MultipartFile file) throws IOException {
        String imageUrl = cloudinaryService.uploadImage(file);
        artist.setImageUrl(imageUrl);
        Artist addArtist = artistService.addArtist(artist);
        return new ResponseEntity<>(addArtist, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Artist artist = artistService.getArtistById(id);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@RequestPart("artist") Artist newArtist,
                                               @RequestParam(value = "file", required = false) MultipartFile file,
                                               @PathVariable Long id) throws IOException {
        if (file != null && !file.isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(file);
            newArtist.setImageUrl(imageUrl);
        } else {
            Artist existingArtist = artistService.getArtistById(id);
            newArtist.setImageUrl(existingArtist.getImageUrl());
        }
        return new ResponseEntity<>(artistService.updateArtist(newArtist, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Album> deleteArtist(@PathVariable Long id){
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}
