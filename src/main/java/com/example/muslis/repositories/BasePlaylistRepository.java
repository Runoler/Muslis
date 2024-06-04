package com.example.muslis.repositories;

import com.example.muslis.models.BasePlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasePlaylistRepository extends JpaRepository<BasePlaylist, Long> {
}
