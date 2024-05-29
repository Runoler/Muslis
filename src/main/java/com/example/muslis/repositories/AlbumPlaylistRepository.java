package com.example.muslis.repositories;

import com.example.muslis.models.AlbumPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumPlaylistRepository extends JpaRepository<AlbumPlaylist, Integer> {
}
