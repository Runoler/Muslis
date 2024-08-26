package com.example.muslis.services;

import com.example.muslis.dtos.ArtistDTO;
import com.example.muslis.dtos.SongDTO;
import com.example.muslis.entities.AlbumRequest;
import com.example.muslis.entities.SongRequest;
import com.example.muslis.models.Album;
import com.example.muslis.models.Artist;
import com.example.muslis.models.Song;
import com.example.muslis.repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NameNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final ArtistService artistService;
    public Song processSongRequest(SongRequest songRequest, Song song) {

        song.setName(songRequest.getSongName());
        song.setListens(0);
        return save(song);
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public void saveSongAudioFileToDirectory(Song song, MultipartFile file) throws Exception {
        try {
            Files.copy(file.getInputStream(), Path.of(song.getAudioPath()), StandardCopyOption.REPLACE_EXISTING);
        } catch (NoSuchFileException e) {
            throw new Exception("Метод Files.copy() не может найти указанный файл или путь к файлу не существует\n"
                    + song.getAudioPath());
        }
    }

    public byte[] loadSongAudioFile(Long id) throws Exception {
        Song song = songRepository.findSongById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return Files.readAllBytes(Path.of(song.getAudioPath()));
    }

    public SongDTO findSongById(Long id) throws Exception{
        Optional<Song> song = songRepository.findSongById(id);
        if (song.isEmpty()) {
            throw new Exception("Song not found.");
        }
        return SongDTO.fromModel(song.get());
    }

    public List<SongDTO> findAllSongs() throws Exception {
        List<Song> songs = songRepository.findAll();
        if (songs.isEmpty())
            throw new Exception("Songs not found.");
        List<SongDTO> songDtos = new ArrayList<>();
        for (Song song : songs) {
            songDtos.add(SongDTO.fromModel(song));
        }
        return songDtos;
    }

    public List<SongDTO> findSongsContain(String namePart) throws NameNotFoundException {
        List<Song> songs = songRepository.findByNameContaining(namePart);
        List<SongDTO> songDtos = new ArrayList<>();
        if (songs.isEmpty())
            throw new NameNotFoundException("Name not found");
        for (Song song : songs) {
            songDtos.add(SongDTO.fromModel(song));
        }
        return songDtos;
    }

    public List<SongDTO> getAllArtistSongs(Long artistId) throws Exception {
        Artist artist = artistService.findArtist(artistId);
        List<Song> songs = songRepository.findByArtist(artist);
        List<SongDTO> songDtos = new ArrayList<>();
        if (!songs.isEmpty()) {
            for (Song song : songs) {
                songDtos.add(SongDTO.fromModel(song));
            }
        }
        return songDtos;
    }
}
