package com.example.muslis.repositories;

import com.example.muslis.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminsRepository extends JpaRepository<Admin, Integer> {
}
