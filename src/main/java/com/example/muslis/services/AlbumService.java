package com.example.muslis.services;

import com.example.muslis.entities.AlbumRequest;
import com.example.muslis.entities.SongRequest;
import com.example.muslis.models.Album;
import com.example.muslis.models.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@SessionScope
public class AlbumService {

    private final SongService songService;
    private final UserService userService;

    public void processAlbumRequest(AlbumRequest albumRequest, List<MultipartFile> files) throws IOException {

        Album album = new Album();
        album.setName(albumRequest.getAlbumName());
        album.setDescription(albumRequest.getAlbumDescription());
        album.setType(albumRequest.getAlbumType());
        album.setId(albumRequest.getArtistId());

        List<Song> songs = new ArrayList<>();
        for (SongRequest songRequest : albumRequest.getSongs()) {
            songs.add(songService.processSongRequest(songRequest));
        }
        album.setSongs(songs);

        album.setArtist(userService.getCurrentUser().getArtistPart());

        userService.save(userService.getCurrentUser());

        for (int i = 0; i < songs.size(); ++i) {
            songService.saveSongAudioFileToDirectory(songs.get(i), files.get(i));
        }
    }
}
