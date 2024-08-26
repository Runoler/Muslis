package com.example.muslis.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playlist_sequence")
    @SequenceGenerator(name = "playlist_sequence", sequenceName = "playlist_seq")
    protected Long id;
    @Column(name = "name")
    protected String name;
    @Column(name = "description")
    protected String description;
}
