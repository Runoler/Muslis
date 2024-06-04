package com.example.muslis.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "user_role", columnDefinition = "VARCHAR(255)")
    private String userRole;

}
