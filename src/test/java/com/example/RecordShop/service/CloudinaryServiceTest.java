package com.example.RecordShop.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

class CloudinaryServiceTest {

    @Mock
    Cloudinary cloudinary;

    @Mock
    Uploader uploader;

    @InjectMocks
    CloudinaryService cloudinaryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        when(cloudinary.uploader()).thenReturn(uploader);
    }

    @Test
    @DisplayName("Empty File Uploaded")
    void ImageUploadEmptyFile() throws IOException {
        MockMultipartFile mockFile = new MockMultipartFile("image", "originalFilename.png", "image/png", new byte[0]);
        assertThrows(IOException.class, () -> cloudinaryService.uploadImage(mockFile));
    }

    @Test
    @DisplayName("Cloudinary Response Issue")
    void ImageUploadCloudinaryResponse() throws IOException {
        MockMultipartFile mockFile = new MockMultipartFile("image", "originalFilename.png", "image/png", "file content".getBytes());
        when(cloudinary.uploader().upload(any(byte[].class), anyMap())).thenReturn(new HashMap<>());
        assertThrows(IOException.class, () -> cloudinaryService.uploadImage(mockFile));
    }

}