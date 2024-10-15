package com.example.RecordShop.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Genre {
    AFROBEATS,
    SOCA,
    R_AND_B,
    HIPHOP,
    JAZZ,
    SALSA,
    BLUES,
    LATIN_ALTERNATIVE,
    ROCK,
    POP,
    CLASSICAL,
    REGGAE,
    DANCEHALL,
    METAL,
    COUNTRY,
    FOLK,
    ELECTRONIC,
    HOUSE,
    TECHNO,
    DISCO,
    GOSPEL,
    FUNK,
    PUNK,
    INDIE,
    GRUNGE,
    KPOP,
    TRAP,
    DRUM_AND_BASS,
    AMBIENT,
    DUBSTEP,
    EDM,
    SYNTHPOP,
    OPERA,
    SWING,
    TRANCE,
    SKA,
    WORLD_MUSIC,
    NEW_WAVE,
    ALTERNATIVE_ROCK,
    PSYCHEDELIC,
    PROGRESSIVE_ROCK,
    POST_ROCK,
    LOFI_HIP_HOP,
    CUMBIA,
    BHANGRA;

    @JsonValue
    public String getFormattedGenre() {
        String genre = this.name().toLowerCase();
        genre = genre.replace("_AND_", "&");
        genre = genre.replace("_", " ");

        String[] genres = genre.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : genres) {
            result.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }

        return result.toString().trim();
    }
}
