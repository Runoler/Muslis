package com.example.muslis.controllers;

import com.example.muslis.security.BasicUserDetails;
import com.example.muslis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String basePage() {
        return "redirect:/auth/login";
    }

    @GetMapping("/home")
    public String homePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return "redirect:auth/login";
        }

        BasicUserDetails basicUserDetails = (BasicUserDetails) authentication.getPrincipal();
        if (basicUserDetails.getBasicUser().getUserRole().equals("ROLE_ADMIN")) {
            return "user/admin-home";
        } else if (basicUserDetails.getBasicUser().getUserRole().equals("ROLE_LISTENER")) {
            return "user/listener-home";
        } else {
            return "user/artist-home";
        }
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return "redirect:auth/login";
        }

        BasicUserDetails basicUserDetails = (BasicUserDetails) authentication.getPrincipal();
        System.out.println(basicUserDetails.getBasicUser());

        return "redirect:home";
    }
}
