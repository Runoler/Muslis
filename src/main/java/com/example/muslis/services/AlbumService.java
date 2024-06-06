package com.example.muslis.services;

import com.example.muslis.dtos.AlbumDTO;
import com.example.muslis.dtos.SongDTO;
import com.example.muslis.entities.AlbumRequest;
import com.example.muslis.entities.SongRequest;
import com.example.muslis.models.Album;
import com.example.muslis.models.Song;
import com.example.muslis.repositories.AlbumRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@SessionScope
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final SongService songService;
    private final UserService userService;

    public void processAlbumRequest(AlbumRequest albumRequest, List<MultipartFile> files) throws Exception {

        Album album = new Album();
        album.setName(albumRequest.getAlbumName());
        album.setDescription(albumRequest.getAlbumDescription());
        album.setType(albumRequest.getAlbumType());
        album.setArtist(userService.getCurrentUser().getArtistPart());

        save(album);

        List<Song> songs = new ArrayList<>();
        for (SongRequest songRequest : albumRequest.getSongs()) {
            Song song = new Song();
            song.setArtist(album.getArtist());
            song.setAlbum(album);
            songService.processSongRequest(songRequest, song);
            songs.add(song);
        }
        album.setSongs(songs);

        save(album);

        for (int i = 0; i < songs.size(); ++i) {
            songService.saveSongAudioFileToDirectory(songs.get(i), files.get(i));
        }
    }

    public AlbumDTO save(Album album) {
        return AlbumDTO.fromModel(albumRepository.save(album));
    }

    public AlbumDTO save(AlbumDTO dto) {
        Album album = new Album();
        album.setId(dto.getId());
        album.setName(dto.getName());
        album.setDescription(dto.getDescription());
        album.setType(dto.getType());

        album.setArtist(userService.getCurrentUser().getArtistPart());

        List<Song> songs = new ArrayList<>();
        for (SongDTO songDto : dto.getSongs()) {
            Song song = new Song();
            song.setId(songDto.getId());
            song.setName(songDto.getName());
            song.setArtist(userService.getCurrentUser().getArtistPart());
            song.setListens(0);
            song.setAlbum(album);
            songs.add(song);
        }
        album.setSongs(songs);

        Album savedAlbum = albumRepository.save(album);
        return AlbumDTO.fromModel(savedAlbum);
    }

}
