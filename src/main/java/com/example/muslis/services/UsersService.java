package com.example.muslis.services;

import com.example.muslis.models.Artist;
import com.example.muslis.models.BasicUser;
import com.example.muslis.models.Listener;
import com.example.muslis.repositories.BasicUsersRepository;
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

    public BasicUser findOne(int id) {
        Optional<BasicUser> foundBasicUser = basicUsersRepository.findById(id);

        return foundBasicUser.orElse(null);
    }

    public BasicUser findByEmail(String email) {
        List<BasicUser> allUsers = basicUsersRepository.findAll();
        Optional<BasicUser> foundUser = allUsers.stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();

        return foundUser.orElse(null);
    }

    public void save(BasicUser user) {

        if (user.getRole() == BasicUser.Role.LISTENER) {
            Listener listener = new Listener(user);
            listenersService.save(listener);
        } else if (user.getRole() == BasicUser.Role.ARTIST) {
            Artist artist = new Artist(user);
            artistsService.save(artist);
        }
    }

}
