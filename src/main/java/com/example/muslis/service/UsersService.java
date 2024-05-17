package com.example.muslis.service;

import com.example.muslis.model.Artist;
import com.example.muslis.model.BasicUser;
import com.example.muslis.model.Listener;
import com.example.muslis.repository.BasicUsersRepository;
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
public class UsersService {

    private BasicUser activeUser;
    private final BasicUsersRepository basicUsersRepository;

    @Autowired
    private ListenersService listenersService;

    @Autowired
    private ArtistsService artistsService;

    @Autowired
    public UsersService(BasicUsersRepository basicUsersRepository) {
        this.basicUsersRepository = basicUsersRepository;
    }

    public List<BasicUser> findAll() {
        return basicUsersRepository.findAll();
    }

    public BasicUser findOne(Long id) {
        Optional<BasicUser> foundBasicUser = basicUsersRepository.findById(id);

        return foundBasicUser.orElse(null);
    }

    public BasicUser findByEmail(String email) {
        Optional<BasicUser> foundedUser = basicUsersRepository.findByEmail(email);
        return foundedUser.orElse(null);
    }

    public void save(BasicUser user) {

        basicUsersRepository.save(user);
        basicUsersRepository.flush();
        if (user.getUserRole() == BasicUser.UserRole.LISTENER) {
            Listener listener = new Listener(user);
            listenersService.save(listener);
        } else if (user.getUserRole() == BasicUser.UserRole.ARTIST) {
            Artist artist = new Artist(user);
            artistsService.save(artist);
        }
    }

}
