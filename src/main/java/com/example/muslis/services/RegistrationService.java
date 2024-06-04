package com.example.muslis.services;

import com.example.muslis.models.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    public void register(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

        userService.save(userInfo);

        System.out.println(userInfo.getUsername() + " is saved.");
    }
}
