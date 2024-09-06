package com.example.RecordShop.controller;

import com.example.RecordShop.model.Artist;
import com.example.RecordShop.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ArtistController {

    @Autowired
    ArtistService artistService;

        @GetMapping
        public ResponseEntity<List<Artist>> getAllAlbums() {
            return new ResponseEntity<>(artistService.getAllArtists(), HttpStatus.OK);
        }
}
