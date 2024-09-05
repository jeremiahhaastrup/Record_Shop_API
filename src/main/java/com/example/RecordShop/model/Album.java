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
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    private String title;

    @Column
    int stock;

    @Column
    int sales;

    @Column
    private Instant releaseDate;

    @Column
    Genre genre;

    @ManyToOne
    @JoinColumn(name = "artistId")
    private Artist artist;
}
