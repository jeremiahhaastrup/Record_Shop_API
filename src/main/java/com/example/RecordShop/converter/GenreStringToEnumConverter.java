package com.example.RecordShop.converter;

import com.example.RecordShop.type.Genre;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenreStringToEnumConverter implements Converter<String, Genre> {
    @Override
    public Genre convert(String source) {
        String formatGenre = source.toUpperCase().replace(" ", "_").replace("&", "_AND_");
        return Genre.valueOf(formatGenre);
    }
}
