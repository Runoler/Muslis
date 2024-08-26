package com.example.muslis.dtos;

import com.example.muslis.models.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListenerDTO {

    private Long id;
    private Long userInfo;
    private List<SongDTO> songs;
    private List<ArtistDTO> artists;
    private List<ListenerPlaylistDTO> playlists;

    public static ListenerDTO fromModel(Listener listener) {
        ListenerDTO dto = new ListenerDTO();
        dto.setId(listener.getId());
        dto.setUserInfo(listener.getUserInfo().getId());

        List<SongDTO> songDtos = new ArrayList<>();
        for (Song song : listener.getSongs()) {
            songDtos.add(SongDTO.fromModel(song));
        }
        dto.setSongs(songDtos);

        List<ArtistDTO> artistDtos = new ArrayList<>();
        for (Artist artist : listener.getArtists()) {
            artistDtos.add(ArtistDTO.fromModel(artist));
        }
        dto.setArtists(artistDtos);

        List<ListenerPlaylistDTO> listenerPlaylistDtos = new ArrayList<>();
        for (ListenerPlaylist listenerPlaylist : listener.getPlaylists()) {
            listenerPlaylistDtos.add(ListenerPlaylistDTO.fromModel(listenerPlaylist));
        }
        dto.setPlaylists(listenerPlaylistDtos);

        return dto;
    }
}
