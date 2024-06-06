package com.example.muslis.controllers;

import com.example.muslis.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String example() {
         return "Hello page!";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }

    @GetMapping("/get-admin")
    public void getAdmin() {
        userService.getAdmin();
    }

    @GetMapping("/api")
    public ResponseEntity<String> basePage() {
        return ResponseEntity.ok("Hello!");
    }

}
