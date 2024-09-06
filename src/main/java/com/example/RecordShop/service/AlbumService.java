package com.example.RecordShop.service;

import com.example.RecordShop.model.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbums();
    Album addAlbum(Album album);
}
