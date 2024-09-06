package com.example.RecordShop.controller;

import com.example.RecordShop.model.Album;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.model.Genre;
import com.example.RecordShop.service.AlbumService;
import com.example.RecordShop.service.AlbumServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerTest {

    @Mock
    private AlbumServiceImpl mockAlbumService;

    @InjectMocks
    private AlbumController albumController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(albumController).build();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("GET /albums")
    void getAllAlbums() throws Exception {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth("1987-10-28").build();
        Artist kendrickLamar = Artist.builder().artist_id(10L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth("1987-06-17").build();

        List<Album> expected = List.of(
                new Album(1L, "Soca Gold 2018", 200, 2500, LocalDate.of(1998, 7, 19), Genre.AFROBEATS, frankOcean),
                new Album(2L, "To Pimp a Butterfly", 150, 2300, LocalDate.of(1998, 7, 19), Genre.HIPHOP, kendrickLamar)
        );

        when(mockAlbumService.getAllAlbums()).thenReturn(expected);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/albums"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].album_id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Soca Gold 2018"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stock").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sales").value(2500))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].releaseDate").value("19/07/1998"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value(Genre.AFROBEATS.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value(frankOcean))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].album_id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("To Pimp a Butterfly"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stock").value(150))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sales").value(2300))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].releaseDate").value("19/07/1998"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].genre").value(Genre.HIPHOP.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist").value(kendrickLamar));
    }
}