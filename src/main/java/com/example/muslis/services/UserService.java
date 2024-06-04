package com.example.muslis.services;

import com.example.muslis.models.Artist;
import com.example.muslis.models.UserInfo;
import com.example.muslis.models.Listener;
import com.example.muslis.repositories.UserInfoRepository;
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

    private final UserInfoRepository userInfoRepository;

    @Autowired
    private ListenerService listenerService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    public UserService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    public UserInfo findOne(Long id) {
        Optional<UserInfo> foundBasicUser = userInfoRepository.findById(id);

        return foundBasicUser.orElse(null);
    }

    public UserInfo findByEmail(String email) {
        Optional<UserInfo> foundedUser = userInfoRepository.findByEmail(email);
        return foundedUser.orElse(null);
    }

    public void save(UserInfo user) {

        userInfoRepository.save(user);
        userInfoRepository.flush();
        if (user.getUserRole().equals("ROLE_LISTENER")) {
            listenerService.save(new Listener(user));
        } else if (user.getUserRole().equals("ROLE_ARTIST")) {
            artistService.save(new Artist(user));
        }
    }

}
