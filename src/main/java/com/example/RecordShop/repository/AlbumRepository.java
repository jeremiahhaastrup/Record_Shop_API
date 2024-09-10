package com.example.RecordShop.repository;

import com.example.RecordShop.model.Album;
import com.example.RecordShop.type.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository <Album, Long> {
    List<Album> findByArtistName(String name);
    List<Album> findByGenre(Genre genre);
}
