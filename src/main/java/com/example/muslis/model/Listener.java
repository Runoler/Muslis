package com.example.muslis.model;

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
@Table(name = "listener")
public class Listener {

    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    private BasicUser user;
    @OneToMany
    @JoinTable(name = "listener_songs")
    private List<Song> songs;
    @OneToMany
    @JoinTable(name = "listener_artists")
    private List<Artist> artists;
    @OneToMany
    @JoinTable(name = "listener_playlists")
    private List<ListenerPlaylist> playlists;
    @OneToMany
    @JoinTable(name = "listener_notifications")
    private List<Notification> notifications;

    public Listener(BasicUser user) {
        this.user = user;
        this.id = user.getId();
    }
}
