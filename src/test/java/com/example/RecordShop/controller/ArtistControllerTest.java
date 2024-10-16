package com.example.RecordShop.controller;

import com.example.RecordShop.model.Artist;
import com.example.RecordShop.service.ArtistServiceImpl;
import com.example.RecordShop.service.CloudinaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistControllerTest {

    @Mock
    private ArtistServiceImpl mockArtistServiceImpl;

    @Mock
    private CloudinaryService cloudinaryService;

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
    void testGetAllArtists() throws Exception {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();
        Artist kendrickLamar = Artist.builder().artist_id(2L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth(LocalDate.of(1987, 7, 17)).build();

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

    @Test
    @DisplayName("POST /artists")
    void testPostArtist() throws Exception {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();

        String artistJson = mapper.writeValueAsString(frankOcean);
        MockMultipartFile mockFile = new MockMultipartFile("file", "originalFilename.png", "image/png", "file content".getBytes());
        MockMultipartFile mockArtist = new MockMultipartFile("artist", "artist.json", "application/json", artistJson.getBytes());

        when(mockArtistServiceImpl.addArtist(frankOcean)).thenReturn(frankOcean);

        this.mockMvcController.perform(multipart("/api/v1/artists")
                        .file(mockFile)
                        .file(mockArtist)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                        .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("GET /artists/{id}")
    void testGetArtistById() throws Exception {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();

        when(mockArtistServiceImpl.getArtistById(frankOcean.getArtist_id())).thenReturn(frankOcean);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/artists/1"))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist_id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Frank Ocean"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.placeOfBirth").value("Long Beach, California, USA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth").value("28/10/1987")).andDo(print());
    }

    @Test
    @DisplayName("PUT /artists")
    void testPutArtist() throws Exception {

        Artist frankOcean = Artist.builder().artist_id(1L).name("Frank Ocean").placeOfBirth("Long Beach, California, USA").dateOfBirth(LocalDate.of(1987, 10, 28)).build();
        Artist kendrickLamar = Artist.builder().artist_id(10L).name("Kendrick Lamar").placeOfBirth("Compton, California, USA").dateOfBirth(LocalDate.of(1987, 6, 17)).build();

        when(mockArtistServiceImpl.getArtistById(frankOcean.getArtist_id())).thenReturn(frankOcean);
        when(mockArtistServiceImpl.updateArtist(frankOcean, 1L)).thenReturn(kendrickLamar);

        this.mockMvcController.perform(MockMvcRequestBuilders.put("/api/v1/artists/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(kendrickLamar)))
                        .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("DELETE /artist")
    void testDeleteArtist() throws Exception{
        Long id = 1L;

        ResultActions result = mockMvcController.perform(
                MockMvcRequestBuilders.delete("/api/v1/artists/{id}", id)
        );
        result.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("File Upload")
    void ImageUpload() throws IOException {
        MockMultipartFile mockFile = new MockMultipartFile("image", "originalFilename.png", "image/png", "file content".getBytes());
        when(cloudinaryService.uploadImage(any(MockMultipartFile.class))).thenReturn("https://url.com/image.png");

        ResponseEntity<String> actual = artistController.uploadArtistImage(mockFile);

        assertAll(
                () -> assertEquals(HttpStatus.OK, actual.getStatusCode()),
                () -> assertEquals("https://url.com/image.png", actual.getBody())
        );
    }
}