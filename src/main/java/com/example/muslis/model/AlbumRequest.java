package com.example.muslis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album_request")
public class AlbumRequest {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="playlist_id", referencedColumnName = "id", nullable = false)
    private AlbumPlaylist playlist;
    @Column(name = "date")
    private Date date;
}
