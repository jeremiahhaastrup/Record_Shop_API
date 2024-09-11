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
    List<Album> findByArtistName(String name);
    List<Album> findByGenre(Genre genre);
    Album findByTitleContainingIgnoreCase(@Param("title") String title);

    @Query(value = "SELECT a FROM album a WHERE YEAR(a.releaseDate) = :year", nativeQuery = true)
    List<Album> findByReleaseYear(@Param("year") int year);
}
