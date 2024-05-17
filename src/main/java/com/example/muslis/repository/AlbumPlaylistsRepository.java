package com.example.muslis.repository;

import com.example.muslis.model.AlbumPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumPlaylistsRepository extends JpaRepository<AlbumPlaylist, Integer> {
}
