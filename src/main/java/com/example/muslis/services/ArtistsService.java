package com.example.muslis.services;

import com.example.muslis.models.Artist;
import com.example.muslis.models.Listener;
import com.example.muslis.repositories.ArtistsRepository;
import com.example.muslis.repositories.ListenersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Artist findByEmail(String email) {
        List<Artist> allArtists = artistsRepository.findAll();
        Optional<Artist> foundArtist = allArtists.stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();

        return foundArtist.orElse(null);
    }

    public void save(Artist artist)
    {
        artistsRepository.save(artist);
    }

}
