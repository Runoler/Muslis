package com.example.muslis.controllers;

import com.example.muslis.models.BasicUser;
import com.example.muslis.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String loginPage(@ModelAttribute("basicUser") BasicUser basicUser) {
        return "auth/login";
    }

    @PostMapping("/login")
    public String performLogin(@ModelAttribute("basicUser") BasicUser basicUser) {
        return "redirect::home";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("basicUser") BasicUser basicUser) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("basicUser") BasicUser basicUser) {
        registrationService.register(basicUser);

        return "redirect:auth/login";
    }
}
