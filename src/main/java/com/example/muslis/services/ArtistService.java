package com.example.muslis.services;

import com.example.muslis.models.Artist;
import com.example.muslis.repositories.ArtistRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Artist findOne(int id) {
        Optional<Artist> foundArtist = artistRepository.findById(id);

        return foundArtist.orElse(null);
    }

    public void save(Artist artist)
    {
        artistRepository.save(artist);
    }

}
