package com.example.RecordShop.model;

public enum Genre {
        AFROBEATS("Afrobeats"),
        SOCA("Soca"),
        RANDB("R&B"),
        HIPHOP("Hip-Hop"),
        JAZZ("Jazz"),
        SALSA("Salsa"),
        BLUES("Blues"),
        LATINALTERNATIVE("Latin Alternative");

    String genre;

    Genre(String genre) {
        this.genre = genre;
    }
}
