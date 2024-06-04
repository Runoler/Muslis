package com.example.muslis.services;

import com.example.muslis.models.UserInfo;
import com.example.muslis.repositories.UserInfoRepository;
import com.example.muslis.security.BasicUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BasicUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> basicUser =  userInfoRepository.findByUsername(username);

        if (basicUser.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new BasicUserDetails(basicUser.get());
    }
}
