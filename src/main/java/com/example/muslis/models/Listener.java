package com.example.muslis.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Listener {

    private List<Song> songs;
    private List<Artist> artists;
    private List<BasePlaylist> basePlaylists;
    private List<Notification> notifications;
}
