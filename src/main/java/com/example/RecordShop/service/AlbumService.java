package com.example.RecordShop.service;

import com.example.RecordShop.model.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    Album addAlbum(Album album);
    Album getAlbumById(Long id);
    Album updateAlbum(Album album, Long id);
    void deleteAlbum(Long id);
    List<Album> getAllAlbumsInStock();
}
