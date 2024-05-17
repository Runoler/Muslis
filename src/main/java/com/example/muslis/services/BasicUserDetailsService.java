package com.example.muslis.services;

import com.example.muslis.models.BasicUser;
import com.example.muslis.repositories.BasicUsersRepository;
import com.example.muslis.security.BasicUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BasicUserDetailsService implements UserDetailsService {
    private final BasicUsersRepository basicUsersRepository;

    @Autowired
    public BasicUserDetailsService(BasicUsersRepository basicUsersRepository) {
        this.basicUsersRepository = basicUsersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BasicUser> basicUser =  basicUsersRepository.findByUsername(username);

        if (basicUser.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new BasicUserDetails(basicUser.get());
    }
}
