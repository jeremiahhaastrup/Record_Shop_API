package com.example.RecordShop.repository;

import com.example.RecordShop.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository <Album, Long> {
}
