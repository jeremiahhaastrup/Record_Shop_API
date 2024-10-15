package com.example.RecordShop.controller;

import com.example.RecordShop.model.Album;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.type.Genre;
import com.example.RecordShop.service.AlbumServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerTest {

    @Mock
    private AlbumServiceImpl mockAlbumServiceImpl;

    @InjectMocks
    private AlbumController albumController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(albumController).build();
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("GET /albums")
    void testGetAllAlbums() throws Exception {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();
        Artist kendrickLamar = Artist.builder().artist_id(10L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth(LocalDate.of(1987, 6, 17)).build();

        List<Album> expected = List.of(
                new Album(1L, "Soca Gold 2018","Soca_Gold_2018.jpeg", 200, 2500, "description", LocalDate.of(1998, 7, 19), Genre.AFROBEATS, frankOcean),
                new Album(2L, "To Pimp a Butterfly", "To_Pimp_a_Butterfly.jpeg", 150, 2300, "description", LocalDate.of(1998, 7, 19), Genre.HIPHOP, kendrickLamar)
        );

        when(mockAlbumServiceImpl.getAllAlbums()).thenReturn(expected);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(expected)))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /albums in stock")
    void testGetAllAlbumsInStock() throws Exception {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();
        Artist kendrickLamar = Artist.builder().artist_id(10L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth(LocalDate.of(1987, 6, 17)).build();

        List<Album> expected = List.of(
                new Album(1L, "Soca Gold 2018", "Soca_Gold_2018.jpeg", 200, 2500, "description", LocalDate.of(1998, 7, 19), Genre.AFROBEATS, frankOcean),
                new Album(2L, "To Pimp a Butterfly", "To_Pimp_a_Butterfly.jpeg", 150, 2300, "description", LocalDate.of(1998, 7, 19), Genre.HIPHOP, kendrickLamar)
        );

        when(mockAlbumServiceImpl.getAllAlbumsInStock()).thenReturn(expected);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums/available")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(expected)))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /albums/artist?name={name}")
    void testGetAllAlbumsByArtist() throws Exception {

        String name = "Frank Ocean";
        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();

        List<Album> expected = List.of(
                new Album(1L, "Soca Gold 2018", "Soca_Gold_2018.jpeg", 200, 2500, "description", LocalDate.of(1998, 7, 19), Genre.AFROBEATS, frankOcean),
                new Album(2L, "To Pimp a Butterfly", "To_Pimp_a_Butterfly.jpeg", 150, 2300, "description", LocalDate.of(1998, 7, 19), Genre.HIPHOP, frankOcean)
        );

        when(mockAlbumServiceImpl.findByArtistNameContainingIgnoreCase(name)).thenReturn(expected);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums/artist?name={name}", name)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expected)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /albums/genre?name={genre}")
    void testGetAllAlbumsByGenre() throws Exception {

        Genre genre = Genre.AFROBEATS;
        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();

        List<Album> expected = List.of(
                new Album(1L, "Soca Gold 2018", "Soca_Gold_2018.jpeg", 200, 2500, "description", LocalDate.of(1998, 7, 19), Genre.AFROBEATS, frankOcean),
                new Album(2L, "To Pimp a Butterfly", "To_Pimp_a_Butterfly.jpeg", 150, 2300, "description", LocalDate.of(1998, 7, 19), Genre.AFROBEATS, frankOcean)
        );

        when(mockAlbumServiceImpl.findByAlbumsGenre(genre)).thenReturn(expected);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums/genre").param("name", genre.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(expected)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /albums/released?year={year}")
    void testGetAllAlbumsByReleaseYear() throws Exception {

        int year = 1998;
        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();

        List<Album> expected = List.of(
                new Album(1L, "Soca Gold 2018", "Soca_Gold_2018.jpeg", 200, 2500, "description", LocalDate.of(1998, 7, 19), Genre.AFROBEATS, frankOcean),
                new Album(2L, "To Pimp a Butterfly", "To_Pimp_a_Butterfly.jpeg", 150, 2300, "description", LocalDate.of(1998, 7, 19), Genre.AFROBEATS, frankOcean)
        );

        when(mockAlbumServiceImpl.findByReleaseYear(year)).thenReturn(expected);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums/released").param("year", String.valueOf(year))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(expected)))
                        .andExpect(status().isOk());

        verify(mockAlbumServiceImpl, times(1)).findByReleaseYear(year);
    }

    @Test
    @DisplayName("GET /albums/title?name={title}")
    void testGetAlbumByTitle() throws Exception {

        String title = "Soca Gold 2018";

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();

        List<Album> expected = List.of(
                new Album(2L, "To Pimp a Butterfly", "To_Pimp_a_Butterfly.jpeg", 150, 2300, "description", LocalDate.of(1998, 7, 19), Genre.AFROBEATS, frankOcean)
        );
        when(mockAlbumServiceImpl.findByTitle(title)).thenReturn(expected);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums/title").param("name", title)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(expected)))
                        .andExpect(status().isOk());

        verify(mockAlbumServiceImpl, times(1)).findByTitle(title);
    }

    @Test
    @DisplayName("POST /albums")
    void testPostAlbum() throws Exception {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 29)).build();

        Album expected = new Album(1L, "Soca Gold 2018", "Soca_Gold_2018.jpeg", 200, 2500, "description", LocalDate.of(1998, 7, 19), Genre.AFROBEATS, frankOcean);

        when(mockAlbumServiceImpl.getAlbumById(expected.getAlbum_id())).thenReturn(expected);

        this.mockMvcController.perform(MockMvcRequestBuilders.post("/api/v1/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(expected)))
                        .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("GET /albums/{id}")
    void testGetAlbumById() throws Exception {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();

        Album album = new Album(1L, "Soca Gold 2018", "Soca_Gold_2018.jpeg", 200, 2500, "description", LocalDate.of(2022, 8, 15), Genre.AFROBEATS, frankOcean);

        when(mockAlbumServiceImpl.getAlbumById(album.getAlbum_id())).thenReturn(album);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(album)))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /albums")
    void testPutAlbum() throws Exception {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();
        Artist kendrickLamar = Artist.builder().artist_id(10L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth(LocalDate.of(1987, 6, 17)).build();

        Album currentAlbum = new Album(1L, "To Pimp a Butterfly", "To_Pimp_a_Butterfly.jpeg", 150, 2300, "description", LocalDate.of(2023, 4, 12), Genre.HIPHOP, kendrickLamar);
        Album newAlbum = new Album(1L, "Soca Gold 2018", "Soca_Gold_2018.jpeg", 200, 2500, "description", LocalDate.of(2022, 8, 15), Genre.AFROBEATS, frankOcean);

        when(mockAlbumServiceImpl.getAlbumById(currentAlbum.getAlbum_id())).thenReturn(currentAlbum);
        when(mockAlbumServiceImpl.updateAlbum(currentAlbum, 1L)).thenReturn(newAlbum);

        this.mockMvcController.perform(MockMvcRequestBuilders.put("/api/v1/albums/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newAlbum)))
                        .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("DELETE /album")
    void testDeleteAlbum() throws Exception {
        Long id = 1L;
        ResultActions result = mockMvcController.perform(
                MockMvcRequestBuilders.delete("/api/v1/albums/{id}", id)
        );
        result.andExpect(status().isNoContent());
    }
}