package com.example.muslis.controllers;

import com.example.muslis.models.UserInfo;
import com.example.muslis.security.ActiveUser;
import com.example.muslis.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/api")
    public ResponseEntity<String> basePage() {
        return ResponseEntity.ok("Hello!");
    }

    @GetMapping("/home")
    public String homePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return "redirect:auth/login";
        }

        ActiveUser activeUser = (ActiveUser) authentication.getPrincipal();
        if (activeUser.getUserInfo().getUserRole().equals("ROLE_ADMIN")) {
            return "activeUser/admin-home";
        } else if (activeUser.getUserInfo().getUserRole().equals("ROLE_LISTENER")) {
            return "activeUser/listener-home";
        } else {
            return "activeUser/artist-home";
        }
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return "redirect:auth/login";
        }

        ActiveUser activeUser = (ActiveUser) authentication.getPrincipal();
        System.out.println(activeUser.getUserInfo());

        return "redirect:home";
    }

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<UserInfo> getAll() throws Exception{
        return this.userInfoService.findAll();
    }
}
