package com.example.muslis.services;

import com.example.muslis.dtos.ArtistDTO;
import com.example.muslis.dtos.ListenerDTO;
import com.example.muslis.models.Artist;
import com.example.muslis.models.Listener;
import com.example.muslis.repositories.ListenerRepository;
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
public class ListenerService {

    private final ListenerRepository listenerRepository;


    public List<ListenerDTO> findAll() {
        List<ListenerDTO> listeners = new ArrayList<>();
        for (Listener listener : listenerRepository.findAll()) {
            listeners.add(ListenerDTO.fromModel(listener));
        }

        return listeners;
    }

    public ListenerDTO findOne(Long id) throws Exception {
        Optional<Listener> listener = listenerRepository.findById(id);
        if (listener.isEmpty())
            throw new Exception("Listener not found.");

        return ListenerDTO.fromModel(listener.get());
    }

    public void save(Listener listener)
    {
        listenerRepository.save(listener);
    }
}
