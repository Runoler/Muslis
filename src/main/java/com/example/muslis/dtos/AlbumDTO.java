package com.example.muslis.dtos;

import com.example.muslis.enums.AlbumType;
import com.example.muslis.models.Album;
import com.example.muslis.models.Song;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AlbumDTO {

    protected Long id;
    protected String name;
    protected String description;
    private AlbumType type;
    private List<SongDTO> songs;
    private Long artist;

    public static AlbumDTO fromModel(Album album) {
        AlbumDTO dto = new AlbumDTO();
        dto.setId(album.getId());
        dto.setName(album.getName());
        dto.setDescription(album.getDescription());
        dto.setType(album.getType());
        dto.setArtist(album.getArtist().getId());

        List<SongDTO> songDtos = new ArrayList<>();
        for (Song song : album.getSongs()) {
            songDtos.add(SongDTO.fromModel(song));
        }
        dto.setSongs(songDtos);

        return dto;
    }
}
