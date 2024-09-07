package com.example.RecordShop.service;

import com.example.RecordShop.exception.AlbumAlreadyExistsException;
import com.example.RecordShop.exception.ArtistAlreadyExistsException;
import com.example.RecordShop.exception.NoSuchAlbumException;
import com.example.RecordShop.exception.NoSuchArtistException;
import com.example.RecordShop.model.Album;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public Artist addArtist(Artist artist) {
        Artist existingArtist = artistRepository.findById(artist.getArtist_id()).orElse(null);
        if (existingArtist == null) {
            return artistRepository.save(artist);
        } else throw new ArtistAlreadyExistsException(STR."\{artist.getName()} already exists!🧐");
    }

    @Override
    public Artist getArtistById(Long id) {
        return artistRepository.findById(id).orElseThrow(
                () -> new NoSuchArtistException(STR."Artist ID \{id} does not exist!👎🏽")
        );
    }
}
