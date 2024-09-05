package com.example.RecordShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, name = "artist_id")
    Long artist_id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    int age;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "dateOfBirth")
    private String placeOfBirth;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    private Set<Album> albumList;

}
