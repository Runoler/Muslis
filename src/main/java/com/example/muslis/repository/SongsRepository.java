package com.example.muslis.repository;

import com.example.muslis.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongsRepository extends JpaRepository<Song, Integer> {
}
