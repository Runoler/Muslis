package com.example.muslis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Artist extends BasicUser {

    private String name;
    private String description;
    @OneToMany
    private List<Song> songs;
    @OneToMany
    private List<BasePlaylist> albums;
    @OneToMany
    private List<Notification> notifications;
    private int subscribers;

    public Artist(BasicUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
