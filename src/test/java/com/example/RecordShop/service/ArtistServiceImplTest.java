package com.example.RecordShop.service;

import com.example.RecordShop.exception.NoSuchArtistException;
import com.example.RecordShop.model.Album;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.repository.ArtistRepository;
import com.example.RecordShop.type.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void postArtist() {

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

    @Test
    @DisplayName("GET /artists/{id} gives an Exception")
    void getArtistByIdReturnsAnException() {
        assertThrows(NoSuchArtistException.class, () -> artistServiceImpl.getArtistById(2L));
    }

    @Test
    @DisplayName("PUT /artists")
    void putArtist() {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth("28/10/1987").build();
        Artist kendrickLamar = Artist.builder().artist_id(1L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth("17/06/1987").build();

        when(mockArtistRepository.findById(frankOcean.getArtist_id())).thenReturn(Optional.of(frankOcean));
        when(mockArtistRepository.save(frankOcean)).thenReturn(kendrickLamar);

        Artist expected = artistServiceImpl.updateArtist(kendrickLamar, 1L);

        assertAll(
                () -> assertEquals(expected.getArtist_id(), kendrickLamar.getArtist_id()),
                () -> assertEquals(expected.getName(), kendrickLamar.getName()),
                () -> assertEquals(expected.getDateOfBirth(), kendrickLamar.getDateOfBirth()),
                () -> assertEquals(expected.getPlaceOfBirth(), kendrickLamar.getPlaceOfBirth())
        );
    }

    @Test
    @DisplayName("DELETE /artist")
    void testDeleteArtist() {

        Long id = 1L;
        artistServiceImpl.deleteArtist(id);
        verify(mockArtistRepository, times(1)).deleteById(id);
    }
}