package com.example.muslis.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public enum Role {
        Listener,
        Artist,
        Admin
    }

    private int id;
    private String username;
    private String password;
    private String email;
    private Role role;
}
