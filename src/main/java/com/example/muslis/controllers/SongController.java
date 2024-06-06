package com.example.muslis.controllers;

import com.example.muslis.dtos.SongDTO;
import com.example.muslis.models.Song;
import com.example.muslis.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    @GetMapping("/audio/{id}")
    public ResponseEntity<byte[]> getAudioFile(@PathVariable("id") Long id) throws Exception {

        try {
            byte[] audioBytes = songService.loadSongAudioFile(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(audioBytes);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getSong(@PathVariable("id") Long id) throws Exception {
        try {
            SongDTO song = songService.findSongById(id);
            System.out.println(song.getName());
            return ResponseEntity.ok(song);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SongDTO>> getAllSongs() throws Exception {
        try {
            List<SongDTO> songs = songService.findAllSongs();
            System.out.println(songs.size());
            return ResponseEntity.ok(songs);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
