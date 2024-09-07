package com.example.RecordShop.controller;

import com.example.RecordShop.model.Album;
import com.example.RecordShop.model.Artist;
import com.example.RecordShop.service.ArtistServiceImpl;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistControllerTest {

    @Mock
    private ArtistServiceImpl mockArtistServiceImpl;

    @InjectMocks
    private ArtistController artistController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(artistController).build();
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("GET /artists")
    void getAllAlbums() throws Exception {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth("28/10/1987").build();
        Artist kendrickLamar = Artist.builder().artist_id(2L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth("17/07/1987").build();

        List<Artist> expected = List.of(
                frankOcean,
                kendrickLamar
        );

        when(mockArtistServiceImpl.getAllArtists()).thenReturn(expected);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/artists"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist_id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Frank Ocean"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].placeOfBirth").value("Long Beach, California, USA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateOfBirth").value("28/10/1987"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].artist_id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Kendrick Lamar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].placeOfBirth").value("Compton, California, USA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dateOfBirth").value("17/07/1987"));
    }
}