package com.example.muslis.repositories;

import com.example.muslis.models.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicUsersRepository extends JpaRepository<BasicUser, Integer> {
}
