package com.example.muslis.repositories;

import com.example.muslis.models.ListenerPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListenerPlaylists extends JpaRepository<ListenerPlaylist, Integer> {
}
