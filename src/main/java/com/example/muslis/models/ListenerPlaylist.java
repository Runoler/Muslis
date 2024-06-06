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
@Table(name = "listener_playlist")
public class ListenerPlaylist extends Playlist{

    @OneToMany
    @JoinTable(name = "listener_playlist_songs")
    private List<Song> songs;
    @ManyToOne
    @JoinColumn(name = "listener_id", referencedColumnName = "id")
    private Listener listener;

}
