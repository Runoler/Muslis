package com.example.muslis.dtos;

import com.example.muslis.models.Album;
import com.example.muslis.models.Artist;
import com.example.muslis.models.Song;
import com.example.muslis.models.UserInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArtistDTO {

    private Long id;
    private Long userInfo;
    private String description;
    private int subscribers;
    private List<SongDTO> songs;
    private List<AlbumDTO> albums;

    public static ArtistDTO fromModel(Artist artist) {
        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());
        dto.setUserInfo(artist.getUserInfo().getId());
        dto.setDescription(artist.getDescription());
        dto.setSubscribers(artist.getSubscribers());

        List<SongDTO> songDtos = new ArrayList<>();
        for (Song song : artist.getSongs()) {
            songDtos.add(SongDTO.fromModel(song));
        }
        dto.setSongs(songDtos);

        List<AlbumDTO> albumDtos = new ArrayList<>();
        for (Album album : artist.getAlbums()) {
            albumDtos.add(AlbumDTO.fromModel(album));
        }
        dto.setAlbums(albumDtos);

        return dto;
    }
}
