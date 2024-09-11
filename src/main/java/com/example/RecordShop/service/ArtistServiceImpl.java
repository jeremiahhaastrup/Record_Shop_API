package com.example.RecordShop.service;

import com.example.RecordShop.exception.ArtistAlreadyExistsException;
import com.example.RecordShop.exception.NoSuchArtistException;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.repository.ArtistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        } else throw new ArtistAlreadyExistsException(String.format("'%s' already exists!🧐", artist.getName()));
    }

    @Override
    public Artist getArtistById(Long id) {
        return artistRepository.findById(id).orElseThrow(
                () -> new NoSuchArtistException(String.format("Artist ID '%s' does not exist!👎🏽", id))
        );
    }

    @Override
    public Artist updateArtist(Artist patchedArtist, Long id) {
        Optional<Artist> currentArtist = artistRepository.findById(id);
        if (currentArtist.isPresent()) {
            BeanUtils.copyProperties(patchedArtist, currentArtist.get(), "album_id");
            return artistRepository.save(currentArtist.get());
        } else {
            throw new NoSuchArtistException(String.format("Artist ID '%s' does not exist!👎🏽", id));
        }
    }

    @Override
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }
}
