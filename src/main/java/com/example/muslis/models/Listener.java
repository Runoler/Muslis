package com.example.muslis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Listener extends BasicUser {

    @OneToMany
    private List<Song> songs;
    @OneToMany
    private List<Artist> artists;
    @OneToMany
    private List<ListenerPlaylist> playlists;
    @OneToMany
    private List<Notification> notifications;

    public Listener(BasicUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
