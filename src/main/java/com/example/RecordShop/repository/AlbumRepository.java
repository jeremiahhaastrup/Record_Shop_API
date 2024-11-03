package com.example.RecordShop.repository;

import com.example.RecordShop.model.Album;
import com.example.RecordShop.type.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository <Album, Long> {
    List<Album> findByArtistNameContainingIgnoreCase(String name);
    List<Album> findByGenre(Genre genre);
    List<Album> findByTitleContainingIgnoreCase(@Param("title") String title);

    @Query(value = "SELECT a FROM Album a JOIN FETCH a.artist WHERE EXTRACT(YEAR FROM a.releaseDate) = :year")
    List<Album> findByReleaseYear(@Param("year") int year);
}
