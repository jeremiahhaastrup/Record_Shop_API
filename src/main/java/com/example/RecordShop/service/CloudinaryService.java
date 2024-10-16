package com.example.RecordShop.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.RecordShop.exception.FailedImageUploadException;
import com.example.RecordShop.exception.ImageFileEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new ImageFileEmptyException("File is empty");
        }

        Map params = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );

        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), params);
            if (result == null || !result.containsKey("secure_url") || result.get("secure_url") == null) {
                throw new FailedImageUploadException("Failed to retrieve secure URL from cloudinary response");
            }
            return (String) result.get("secure_url");
        } catch (RuntimeException e) {
            throw new FailedImageUploadException("Upload failed to Cloudinary");
        }
    }
}
