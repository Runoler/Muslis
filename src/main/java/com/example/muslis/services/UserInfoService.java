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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ListenerService listenerService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public List<UserInfo> findAll() throws Exception{
        List<UserInfo> users =  userInfoRepository.findAll();

        if (users.isEmpty())
            throw new UsernameNotFoundException("ActiveUser not found!");

        return users;

    }

    public void save(UserInfo user) {
        userInfoRepository.save(user);
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

}
