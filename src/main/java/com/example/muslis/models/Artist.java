package com.example.muslis.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    private String name;
    private String description;
    private List<Song> songs;
    private List<BasePlaylist> albums;
    private int subscribers;
}
