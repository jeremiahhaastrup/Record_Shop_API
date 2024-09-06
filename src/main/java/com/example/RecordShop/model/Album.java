package com.example.RecordShop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
