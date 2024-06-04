package com.example.muslis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class Album {

    public enum AlbumType {
        Single,
        Album
    }
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name="playlist_id", referencedColumnName = "id", nullable = false)
    private BasePlaylist basePlaylist;
    @Enumerated(EnumType.STRING)
    @Column(name = "album_type")
    private AlbumType type;
    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;

    public Album(BasePlaylist basePlaylist, AlbumType type) {
        this.basePlaylist = basePlaylist;
        this.id = basePlaylist.getId();
        this.type = type;
    }
}
