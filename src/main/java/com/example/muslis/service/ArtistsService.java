package com.example.muslis.service;

import com.example.muslis.model.Artist;
import com.example.muslis.repository.ArtistsRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ArtistsService {

    private Artist activeUser;
    private final ArtistsRepository artistsRepository;

    @Autowired
    public ArtistsService(ArtistsRepository artistsRepository) {
        this.artistsRepository = artistsRepository;
    }

    public List<Artist> findAll() {
        return artistsRepository.findAll();
    }

    public Artist findOne(int id) {
        Optional<Artist> foundArtist = artistsRepository.findById(id);

        return foundArtist.orElse(null);
    }

    public void save(Artist artist)
    {
        artistsRepository.save(artist);
    }

}
