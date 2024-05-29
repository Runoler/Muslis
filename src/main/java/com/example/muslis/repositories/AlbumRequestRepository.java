package com.example.muslis.repositories;

import com.example.muslis.models.AlbumRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRequestRepository extends JpaRepository<AlbumRequest, Integer> {
}
