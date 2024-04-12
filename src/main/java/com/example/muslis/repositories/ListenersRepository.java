package com.example.muslis.repositories;

import com.example.muslis.models.Listener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListenersRepository extends JpaRepository<Listener, Integer> {
}
