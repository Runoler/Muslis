package com.example.muslis.controllers;

import com.example.muslis.models.UserInfo;
import com.example.muslis.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;

    @Autowired
    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginPage(@ModelAttribute("basicUser") UserInfo userInfo) {

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public String performLogin(@ModelAttribute("basicUser") UserInfo userInfo) {
        return "redirect::home";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("basicUser") UserInfo userInfo) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("basicUser") UserInfo userInfo) {
        registrationService.register(userInfo);

        return "redirect:auth/login";
    }
}
