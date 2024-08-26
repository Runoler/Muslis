package com.example.muslis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private UserInfo userInfo;
    @OneToMany
    @JoinTable(name = "listener_songs")
    private List<Song> songs;
    @OneToMany
    @JoinTable(name = "listener_artists")
    private List<Artist> artists;
    @OneToMany(mappedBy = "listener", cascade = CascadeType.ALL)
    private List<ListenerPlaylist> playlists;

    public Listener(UserInfo userInfo) {
        this.userInfo = userInfo;
        this.id = userInfo.getId();
    }
}
