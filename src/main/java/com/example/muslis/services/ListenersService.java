package com.example.muslis.services;

import com.example.muslis.models.Listener;
import com.example.muslis.repositories.ListenersRepository;
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
public class ListenersService {

    private Listener activeUser;
    private final ListenersRepository listenersRepository;

    @Autowired
    public ListenersService(ListenersRepository listenersRepository) {
        this.listenersRepository = listenersRepository;
    }

    public List<Listener> findAll() {
        return listenersRepository.findAll();
    }

    public Listener findOne(int id) {
        Optional<Listener> foundListener = listenersRepository.findById(id);

        return foundListener.orElse(null);
    }

    public void save(Listener listener)
    {
        listenersRepository.save(listener);
    }
}
