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
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "listener_playlist")
public class ListenerPlaylist {

    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "base_playlist_id", referencedColumnName = "id", nullable = false)
    private BasePlaylist basePlaylist;
    @Column(name = "subscribers")
    private int subscribers;

    public ListenerPlaylist(BasePlaylist basePlaylist) {
        this.basePlaylist = basePlaylist;
        this.id = basePlaylist.getId();
    }
}
