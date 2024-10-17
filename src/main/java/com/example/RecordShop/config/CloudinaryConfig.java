

package com.example.RecordShop.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Value("${CLOUDINARY_URL}")
    private String cloudinaryUrl;

    @Bean
    public Cloudinary cloudinary() {

        if (cloudinaryUrl == null && cloudinaryUrl.isEmpty()) {
            cloudinaryUrl = System.getenv("CLOUDINARY_URL");
        }

        if (cloudinaryUrl == null || cloudinaryUrl.isEmpty()) {
            throw new IllegalStateException("CLOUDINARY_URL env variable is empty or not set");
        }


        Cloudinary cloudinary = new Cloudinary(this.cloudinaryUrl);
        cloudinary.config.secure = true;
        return cloudinary;
    }
}

