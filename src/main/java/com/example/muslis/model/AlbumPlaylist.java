package com.example.muslis.model;

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
@Table(name = "album_playlist")
public class AlbumPlaylist {

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

    public AlbumPlaylist(BasePlaylist basePlaylist, AlbumType type) {
        this.basePlaylist = basePlaylist;
        this.id = basePlaylist.getId();
        this.type = type;
    }
}
