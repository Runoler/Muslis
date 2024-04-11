package com.example.muslis.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumPlaylist {

    private enum AlbumType {
        Single,
        Album
    }
    private BasePlaylist playlist;
    private AlbumType type;
}
