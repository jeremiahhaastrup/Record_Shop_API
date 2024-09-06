package com.example.RecordShop.service;

import com.example.RecordShop.model.Album;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.model.Genre;
import com.example.RecordShop.repository.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumServiceImplTest {

    @Mock
    private AlbumRepository mockAlbumRepository;

    @InjectMocks
    private AlbumServiceImpl albumServiceImpl;

    @Test
    @DisplayName("GET /albums")
    void getAllAlbums() {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth("28/10/1987").build();
        Artist kendrickLamar = Artist.builder().artist_id(10L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth("17/06/1987").build();

        List<Album> expected = List.of(
                new Album(1L, "Soca Gold 2018", 200, 2500, LocalDate.of(2022, 8, 15), Genre.AFROBEATS, frankOcean),
                new Album(2L, "To Pimp a Butterfly", 150, 2300, LocalDate.of(2023, 4, 12), Genre.HIPHOP, kendrickLamar)
        );

        when(mockAlbumRepository.findAll()).thenReturn(expected);

        List<Album> actual = albumServiceImpl.getAllAlbums();

        assertIterableEquals(actual, expected);
    }

    @Test
    @DisplayName("POST /albums")
    void postAlbum() {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth("28/10/1987").build();
        Artist kendrickLamar = Artist.builder().artist_id(10L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth("17/06/1987").build();

        Album expected1 = new Album(1L, "Soca Gold 2018", 200, 2500, LocalDate.of(2022, 8, 15), Genre.AFROBEATS, frankOcean);
        Album expected2 = new Album(2L, "To Pimp a Butterfly", 150, 2300, LocalDate.of(2023, 4, 12), Genre.HIPHOP, kendrickLamar);

        when(mockAlbumRepository.save(expected1)).thenReturn(expected1);
        when(mockAlbumRepository.save(expected2)).thenReturn(expected2);

        Album actual1 = albumServiceImpl.addAlbum(expected1);
        Album actual2 = albumServiceImpl.addAlbum(expected2);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
}