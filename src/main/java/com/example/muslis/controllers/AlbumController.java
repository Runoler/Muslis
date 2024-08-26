package com.example.muslis.controllers;

import com.example.muslis.entities.AlbumRequest;
import com.example.muslis.services.AlbumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/artist")
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping("/create-album")
    public ResponseEntity<?> createAlbum(@RequestParam("mp3files") List<MultipartFile> files,
                                         @ModelAttribute @Valid AlbumRequest albumRequest) throws Exception {
        albumService.processAlbumRequest(albumRequest, files);
        return ResponseEntity.ok().build();
    }
}
