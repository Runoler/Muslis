package com.example.muslis.repository;

import com.example.muslis.model.AlbumRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRequestsRepository extends JpaRepository<AlbumRequest, Integer> {
}
