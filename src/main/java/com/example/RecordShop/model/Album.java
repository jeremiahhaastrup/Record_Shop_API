package com.example.RecordShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false, name = "album_id")
    Long album_id;

    @Column(name = "title")
    private String title;

    @Column(name = "stock")
    int stock;

    @Column(name = "sales")
    int sales;

    @Column(name = "releaseDate")
    private Instant releaseDate;

    @OneToOne(mappedBy = "genre", fetch = FetchType.LAZY)
    @Column(name = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
