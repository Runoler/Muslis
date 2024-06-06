package com.example.muslis.controllers;

import com.example.muslis.entities.AlbumRequest;
import com.example.muslis.services.AlbumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping("/create-album")
    public ResponseEntity<?> createAlbum(@RequestBody @Valid AlbumRequest albumRequest,
                                         @RequestParam("mp3files") List<MultipartFile> files) throws IOException {
        albumService.processAlbumRequest(albumRequest, files);
        return ResponseEntity.ok().build();
    }
}
