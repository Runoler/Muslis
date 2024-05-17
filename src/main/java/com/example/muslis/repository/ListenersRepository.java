package com.example.muslis.repository;

import com.example.muslis.model.Listener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListenersRepository extends JpaRepository<Listener, Integer> {
}
