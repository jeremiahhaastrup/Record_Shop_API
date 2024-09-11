package com.example.RecordShop.service;

import com.example.RecordShop.exception.AlbumAlreadyExistsException;
import com.example.RecordShop.exception.NoSuchAlbumException;
import com.example.RecordShop.model.Album;
import com.example.RecordShop.repository.AlbumRepository;
import com.example.RecordShop.type.Genre;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"AlbumCache"})
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
    public List<Album> getAllAlbumsInStock() {
        List<Album> albums = new ArrayList<>();
        for (Album album : albumRepository.findAll()) {
            if (album.getStock() >= 1) {
                albums.add(album);
            }
        }
        return albums;
    }

    @Cacheable
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

    @CachePut
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

    @CacheEvict
    @Override
    public void deleteAlbum(Long id) {
       albumRepository.deleteById(id);
    }

    @Override
    public List<Album> findByArtistNameContainingIgnoreCase(String name) {
        return albumRepository.findByArtistNameContainingIgnoreCase(name);
    }

    @Override
    public List<Album> findByAlbumsGenre(Genre genre) {
        return albumRepository.findByGenre(genre);
    }

    @Override
    public List<Album> findByReleaseYear(int year) {
        return albumRepository.findByReleaseYear(year);
    }

    @Override
    public List<Album> findByTitle(String title) {
        return albumRepository.findByTitleContainingIgnoreCase(title);
    }
}
