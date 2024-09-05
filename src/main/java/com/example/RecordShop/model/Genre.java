package com.example.RecordShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public enum Genre {
        AFROBEATS("Afrobeats"),
        SOCA("Soca"),
        RANDB("R&B"),
        HIPHOP("Hip-Hop"),
        JAZZ("Jazz"),
        SALSA("Salsa"),
        BLUES("Blues"),
        LATINALTERNATIVE("Latin Alternative");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    Long genre_id;

    @OneToOne(mappedBy = "album", fetch = FetchType.LAZY)
    Album album;

    @Column(name = "name")
    String genre;

    Genre(String genre) {
        this.genre = genre;
    }
}
