package com.example.muslis.entities;

import com.example.muslis.enums.AlbumType;
import com.example.muslis.models.Album;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
public class AlbumRequest {

    private String albumName;
    private String albumDescription;
    private AlbumType albumType;
    private Long artistId;
    private List<SongRequest> songs;
}
