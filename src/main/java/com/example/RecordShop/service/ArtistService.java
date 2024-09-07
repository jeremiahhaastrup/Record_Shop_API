package com.example.RecordShop.service;

import com.example.RecordShop.model.Album;
import com.example.RecordShop.model.Artist;

import java.util.List;

public interface ArtistService {
    List<Artist> getAllArtists();
    Artist addArtist(Artist artist);
}
