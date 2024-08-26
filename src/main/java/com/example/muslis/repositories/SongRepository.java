package com.example.muslis.repositories;

import com.example.muslis.dtos.ArtistDTO;
import com.example.muslis.models.Artist;
import com.example.muslis.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findSongById(Long id);
    List<Song> findByNameContaining(String s);

    List<Song> findByArtist(Artist artist);
}
