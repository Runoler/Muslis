package com.example.muslis.controllers;

import com.example.muslis.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/listener")
    public ResponseEntity<String> basePage() {
        return ResponseEntity.ok("A listener page here.");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> exampleAdmin() {
        return ResponseEntity.ok("An admin page here.");
    }

    @GetMapping("/get-admin")
    public ResponseEntity<String> getAdmin() {
        userService.getAdmin();
        return ResponseEntity.ok("You are an admin now.");
    }

    @GetMapping("/artist")
    public ResponseEntity<String> artistPage() {
        return ResponseEntity.ok("An artist page here.");
    }

    @GetMapping("/get-artist")
    public ResponseEntity<String> getArtist() {
        userService.getArtist();
        return ResponseEntity.ok("You are an admin now.");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        userService.logout();
        return ResponseEntity.ok("You are not authenticated yet.");
    }

}
