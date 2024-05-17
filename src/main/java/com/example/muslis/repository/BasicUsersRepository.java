package com.example.muslis.repository;

import com.example.muslis.model.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicUsersRepository extends JpaRepository<BasicUser, Long> {

    Optional<BasicUser> findByEmail(String email);

}
