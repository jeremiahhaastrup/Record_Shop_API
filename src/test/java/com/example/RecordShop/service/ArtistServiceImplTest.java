package com.example.RecordShop.service;

import com.example.RecordShop.exception.NoSuchArtistException;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.repository.ArtistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistServiceImplTest {

    @Mock
    private ArtistRepository mockArtistRepository;

    @InjectMocks
    private ArtistServiceImpl artistServiceImpl;

    @Test
    @DisplayName("GET /artists")
    void getAllArtists() {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth("28/10/1987").build();
        Artist kendrickLamar = Artist.builder().artist_id(10L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth("17/06/1987").build();

        List<Artist> expected = List.of(
                frankOcean,
                kendrickLamar
        );

        when(mockArtistRepository.findAll()).thenReturn(expected);

        List<Artist> actual = artistServiceImpl.getAllArtists();

        assertIterableEquals(actual, expected);
    }

    @Test
    @DisplayName("POST /artists")
    void postAlbum() {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth("28/10/1987").build();
        Artist kendrickLamar = Artist.builder().artist_id(10L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth("17/06/1987").build();

        when(mockArtistRepository.save(frankOcean)).thenReturn(frankOcean);
        when(mockArtistRepository.save(kendrickLamar)).thenReturn(kendrickLamar);

        Artist actual1 = artistServiceImpl.addArtist(frankOcean);
        Artist actual2 = artistServiceImpl.addArtist(kendrickLamar);

        assertEquals(frankOcean, actual1);
        assertEquals(kendrickLamar, actual2);
    }


    @Test
    @DisplayName("GET /artists/{id}")
    void getArtistById() {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth("28/10/1987").build();

        when(mockArtistRepository.findById(frankOcean.getArtist_id())).thenReturn(Optional.of(frankOcean));

        Artist actual = artistServiceImpl.getArtistById(frankOcean.getArtist_id());

        assertEquals(actual, frankOcean);
    }

}