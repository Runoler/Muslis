package com.example.muslis.services;

import com.example.muslis.models.BasicUser;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    public void register(BasicUser basicUser) {
        basicUser.setPassword(passwordEncoder.encode(basicUser.getPassword()));

        userService.save(basicUser);

        System.out.println(basicUser.getUsername() + " is saved.");
    }
}
