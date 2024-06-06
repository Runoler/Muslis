package com.example.muslis.services;

import com.example.muslis.dtos.ArtistDTO;
import com.example.muslis.models.Artist;
import com.example.muslis.repositories.ArtistRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public List<ArtistDTO> findAll() {
        List<ArtistDTO> artists = new ArrayList<>();
        for (Artist artist : artistRepository.findAll()) {
            artists.add(ArtistDTO.fromModel(artist));
        }

        return artists;
    }

    public ArtistDTO findOne(Long id) throws Exception {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isEmpty())
            throw new Exception("Artist not found.");

        return ArtistDTO.fromModel(artist.get());
    }

    public void save(ArtistDTO dto)
    {
        Artist artist = artistRepository.findById(dto.getId()).get();

        artistRepository.save(artist);
    }

    public void updateListener(Artist artist, ArtistDTO dto) {
        artist.setDescription(dto.getDescription());
    }

}
