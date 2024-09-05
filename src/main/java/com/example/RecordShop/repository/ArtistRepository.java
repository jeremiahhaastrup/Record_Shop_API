package com.example.RecordShop.repository;

import com.example.RecordShop.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist,Long> {
}
