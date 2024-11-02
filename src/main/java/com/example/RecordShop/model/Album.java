package com.example.RecordShop.model;

import com.example.RecordShop.type.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "^https://.*\\.(jpg|jpeg|png|gif)$", message = "Image URL must be a valid HTTPS URL with a valid image extension (jpg, jpeg, png, or gif)")
    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "stock")
    int stock;

    @Column(name = "sales")
    int sales;

    @Column(name = "description")
    private String description;

    @Column(name = "release_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    @Column(name = "genre_id")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne()
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
