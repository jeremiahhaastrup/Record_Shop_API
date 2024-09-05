package com.example.RecordShop.model;

public enum Genre {
    AFROBEATS("Afrobeats"),
    POP("Pop"),
    SOCA("Soca"),
    JAZZ("Jazz"),
    BLUES("Blues"),
    HIPHOP("Hip-Hop"),
    SALSA("Salsa"),
    RANDB("R&B");

    String genre;

    Genre(String genre) {
        this.genre = genre;
    }
}
