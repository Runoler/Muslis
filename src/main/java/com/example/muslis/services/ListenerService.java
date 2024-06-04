package com.example.muslis.services;

import com.example.muslis.models.Listener;
import com.example.muslis.repositories.ListenerRepository;
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
public class ListenerService {

    private final ListenerRepository listenerRepository;


    public List<Listener> findAll() {
        return listenerRepository.findAll();
    }

    public Listener findOne(Long id) {
        Optional<Listener> foundListener = listenerRepository.findById(id);

        return foundListener.orElse(null);
    }

    public void save(Listener listener)
    {
        listenerRepository.save(listener);
    }
}
