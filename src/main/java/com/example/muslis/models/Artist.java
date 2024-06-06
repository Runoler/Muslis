package com.example.muslis.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private UserInfo userInfo;
    @Column(name = "description")
    private String description;
    @Column(name = "subscribers")
    private int subscribers;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Song> songs;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Album> albums;

    public Artist(UserInfo userInfo) {
        this.userInfo = userInfo;
        this.id = userInfo.getId();
    }
}
