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
@Table(name = "artist")
public class Artist {

    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    private BasicUser user;
    @Column(name = "description")
    private String description;
    @OneToMany
    @JoinTable(name = "artist_songs")
    private List<Song> songs;
    @OneToMany
    @JoinTable(name = "artist_albums")
    private List<AlbumPlaylist> albums;
    @OneToMany
    @JoinTable(name = "artist_notifications")
    private List<Notification> notifications;
    @Column(name = "subscribers")
    private int subscribers;

    public Artist(BasicUser user) {
        this.user = user;
        this.id = user.getId();
    }
}
