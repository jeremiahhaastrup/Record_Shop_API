package com.example.RecordShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

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

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "placeOfBirth")
    private String placeOfBirth;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    private List<Album> albumList;

}
