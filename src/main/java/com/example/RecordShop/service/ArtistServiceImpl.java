package com.example.RecordShop.service;

import com.example.RecordShop.model.Artist;
import com.example.RecordShop.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        artistRepository.findAll().forEach(artists::add);
        return artists;
    }
}
