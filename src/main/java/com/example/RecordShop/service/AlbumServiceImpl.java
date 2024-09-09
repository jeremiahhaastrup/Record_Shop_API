package com.example.RecordShop.service;

import com.example.RecordShop.exception.AlbumAlreadyExistsException;
import com.example.RecordShop.exception.NoSuchAlbumException;
import com.example.RecordShop.model.Album;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.repository.AlbumRepository;
import com.example.RecordShop.repository.ArtistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumRepository.findAll().forEach(albums::add);
        return albums;
    }

    @Override
    public Album getAlbumById(Long id) {
        return albumRepository.findById(id).orElseThrow(
                () -> new NoSuchAlbumException(String.format("Album ID '%s' does not exist!👎🏽", id))
        );
    }

    @Override
    public Album addAlbum(Album album) {
        Album existingAlbum = albumRepository.findById(album.getAlbum_id()).orElse(null);
        if (existingAlbum == null) {
            return albumRepository.save(album);
        } else throw new AlbumAlreadyExistsException(String.format("'%s' already exists!🧐", album.getTitle()));
    }

    @Override
    public Album updateAlbum(Album patchedAlbum, Long id) {
        Optional<Album> currentAlbum = albumRepository.findById(id);
        if (currentAlbum.isPresent()) {
            BeanUtils.copyProperties(patchedAlbum, currentAlbum.get(), "album_id");
            return albumRepository.save(currentAlbum.get());
        } else {
            throw new NoSuchAlbumException(String.format("Album ID '%s' does not exist!👎🏽", id));
        }
    }
}
