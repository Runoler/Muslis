package com.example.muslis.repositories;

import com.example.muslis.models.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicUserRepository extends JpaRepository<BasicUser, Long> {

    Optional<BasicUser> findByUsername(String username);
    Optional<BasicUser> findByEmail(String email);

}
