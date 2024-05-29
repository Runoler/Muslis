package com.example.muslis.services;

import com.example.muslis.models.Artist;
import com.example.muslis.models.BasicUser;
import com.example.muslis.models.Listener;
import com.example.muslis.repositories.BasicUserRepository;
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
public class UserService {

    private final BasicUserRepository basicUserRepository;

    @Autowired
    private ListenerService listenerService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    public UserService(BasicUserRepository basicUserRepository) {
        this.basicUserRepository = basicUserRepository;
    }

    public List<BasicUser> findAll() {
        return basicUserRepository.findAll();
    }

    public BasicUser findOne(Long id) {
        Optional<BasicUser> foundBasicUser = basicUserRepository.findById(id);

        return foundBasicUser.orElse(null);
    }

    public BasicUser findByEmail(String email) {
        Optional<BasicUser> foundedUser = basicUserRepository.findByEmail(email);
        return foundedUser.orElse(null);
    }

    public void save(BasicUser user) {

        basicUserRepository.save(user);
        basicUserRepository.flush();
        if (user.getUserRole().equals("ROLE_LISTENER")) {
            Listener listener = new Listener(user);
            listenerService.save(listener);
        } else if (user.getUserRole().equals("ROLE_ARTIST")) {
            Artist artist = new Artist(user);
            artistService.save(artist);
        }
    }

}
