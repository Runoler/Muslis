package com.example.muslis.services;

import com.example.muslis.entities.SongRequest;
import com.example.muslis.models.Song;
import com.example.muslis.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    public Song processSongRequest(SongRequest songRequest) {

        Song song = new Song();
        song.setName(songRequest.getSongName());
        return song;
    }

    public void saveSongAudioFileToDirectory(Song song, MultipartFile file) throws IOException {
        Files.copy(file.getInputStream(), Path.of(song.getAudioPath()), StandardCopyOption.REPLACE_EXISTING);
    }

    public byte[] loadSongAudioFile(Long id) throws Exception {
        Song song = this.findSongById(id);
        return Files.readAllBytes(Path.of(song.getAudioPath()));
    }

    public Song findSongById(Long id) throws Exception{
        Optional<Song> song = songRepository.findSongById(id);
        if (song.isEmpty()) {
            throw new Exception("Song not found.");
        }
        return song.get();
    }

    public List<Song> findAllSongs() throws Exception {
        List<Song> songs = songRepository.findAll();
        if (songs.isEmpty())
            throw new Exception("Songs not found.");
        return songs;
    }
}
