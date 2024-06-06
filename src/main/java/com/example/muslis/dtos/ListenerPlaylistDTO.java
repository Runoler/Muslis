package com.example.muslis.dtos;

import com.example.muslis.models.Listener;
import com.example.muslis.models.ListenerPlaylist;
import com.example.muslis.models.Song;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListenerPlaylistDTO {

    protected Long id;
    protected String name;
    protected String description;
    private List<SongDTO> songs;
    private Long listener;

    public static ListenerPlaylistDTO fromModel(ListenerPlaylist listenerPlaylist) {
        ListenerPlaylistDTO dto = new ListenerPlaylistDTO();
        dto.setId(listenerPlaylist.getId());
        dto.setName(listenerPlaylist.getName());
        dto.setDescription(listenerPlaylist.getDescription());
        dto.setListener(listenerPlaylist.getListener().getId());

        List<SongDTO> songDtos = new ArrayList<>();
        for (Song song : listenerPlaylist.getSongs()) {
            songDtos.add(SongDTO.fromModel(song));
        }

        dto.setSongs(songDtos);
        return dto;
    }
}
