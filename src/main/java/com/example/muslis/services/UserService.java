package com.example.muslis.services;

import com.example.muslis.enums.Role;
import com.example.muslis.models.Artist;
import com.example.muslis.models.UserInfo;
import com.example.muslis.models.Listener;
import com.example.muslis.repositories.UserInfoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ListenerService listenerService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    public UserService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public List<UserInfo> findAll() throws Exception{
        List<UserInfo> users =  userInfoRepository.findAll();

        if (users.isEmpty())
            throw new UsernameNotFoundException("ActiveUser not found!");

        return users;

    }

    public UserInfo save(UserInfo user) {
        return userInfoRepository.save(user);
    }

    public UserInfo create(UserInfo user) {
        if (userInfoRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (userInfoRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    public UserInfo getByUsername(String username) {
        return userInfoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserInfo getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public void GiveArtistRole(UserInfo userInfo) {
        Artist artist = new Artist();
        userInfo.setArtistPart(artist);
        userInfo.setUserRole(Role.ROLE_ARTIST);
        save(userInfo);
    }

    public void GiveListenerRole(UserInfo userInfo) {
        Listener listener = new Listener();
        userInfo.setListenerPart(listener);
        userInfo.setUserRole(Role.ROLE_LISTENER);
        save(userInfo);
    }

    public void getAdmin() {
        var user = getCurrentUser();
        user.setUserRole(Role.ROLE_ADMIN);
        save(user);
    }

}
