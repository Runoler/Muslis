package com.example.muslis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BasicUser {

    public enum Role {
        LISTENER,
        ARTIST,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    protected String username;
    protected String password;
    protected String email;
    @Enumerated(EnumType.STRING)
    protected Role role;
}
