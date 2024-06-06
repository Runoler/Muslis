package com.example.muslis.services;

import com.example.muslis.enums.Role;
import com.example.muslis.models.Artist;
import com.example.muslis.models.Listener;
import com.example.muslis.models.UserInfo;
import com.example.muslis.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> user =  userInfoRepository.findByUsername(username);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return user.get();
    }

    public List<UserInfo> findAll() throws Exception{
        List<UserInfo> users =  userInfoRepository.findAll();

        if (users.isEmpty())
            throw new UsernameNotFoundException("Users not found!");

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

    public void getArtist() {
        var user = getCurrentUser();
        user.setUserRole(Role.ROLE_ARTIST);
        save(user);
    }

    public void logout() {
        this.save(getCurrentUser());
        SecurityContextHolder.clearContext();
    }

}
