package com.example.RecordShop.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
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
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    private String name;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column
    private Date dateOfBirth;

    @Column
    int age;

    @Column
    String placeOfBirth;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    private Set<Album> albumList;

}
