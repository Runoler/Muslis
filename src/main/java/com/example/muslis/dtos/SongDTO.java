package com.example.muslis.dtos;

import com.example.muslis.models.Song;
import lombok.Data;

@Data
public class SongDTO {

    private Long id;
    private String name;
    private Long artist;
    private Long album;
    private Long listens;

    public static SongDTO fromModel(Song song) {
        SongDTO dto = new SongDTO();
        dto.setId(song.getId());
        dto.setName(song.getName());
        dto.setArtist(song.getArtist().getId());
        dto.setAlbum(song.getAlbum().getId());
        dto.setListens(song.getListens());

        return dto;
    }
}
