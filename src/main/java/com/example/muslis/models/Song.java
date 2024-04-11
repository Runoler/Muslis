package com.example.muslis.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song {

    private String name;
    private AudioFile audioFile;
    private Artist artist;
    private BasePlaylist album;
    private int listens;
}
