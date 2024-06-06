package com.example.muslis.models;

import com.example.muslis.enums.AlbumType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "album")
public class Album extends Playlist{

    @Enumerated(EnumType.STRING)
    @Column(name = "album_type")
    private AlbumType type;
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Song> songs;
    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;
}
