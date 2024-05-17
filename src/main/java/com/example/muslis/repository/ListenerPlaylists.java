package com.example.muslis.repository;

import com.example.muslis.model.ListenerPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListenerPlaylists extends JpaRepository<ListenerPlaylist, Integer> {
}
