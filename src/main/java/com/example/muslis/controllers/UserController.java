package com.example.muslis.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model) {
        return "registration successful";
    }
}
