package com.example.RecordShop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, name = "artist_id")
    Long artist_id;

    @Column(name = "name")
    private String name;

    @Pattern(regexp = "^https://.*\\.(jpg|jpeg|png|gif)$", message = "Image URL must be a valid HTTPS URL with a valid image extension (jpg, jpeg, png, or gif)")
    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "biography")
    private String biography;

    @Column(name = "dateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @Column(name = "placeOfBirth")
    private String placeOfBirth;

}
