package com.example.muslis.controller;

import com.example.muslis.model.BasicUser;
import com.example.muslis.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    public String basePage() {
        return "redirect:authorise";
    }

    @GetMapping("/authorise")
    public String authorise(Model model) {
        model.addAttribute("user", new BasicUser());
        return "authorise";
    }
    @PostMapping("/authorise")
    public String handleAuthorise(@ModelAttribute("user") BasicUser user) {
        BasicUser existingUser = usersService.findByEmail(user.getEmail());
        if (existingUser == null) {
            return "/authorise";
        }
        usersService.setActiveUser(usersService.findOne(existingUser.getId()));
        return "redirect:home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new BasicUser());
        model.addAttribute("Roles", BasicUser.UserRole.values());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("user") BasicUser user) {
        BasicUser existingUser = usersService.findByEmail(user.getEmail());
        if (existingUser == null) {
            usersService.save(user);
        }
        return "redirect:authorise";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        if (usersService.getActiveUser() == null) {
            return "redirect:authorise";
        }
        if (usersService.getActiveUser().getUserRole().equals(BasicUser.UserRole.LISTENER)) {
            return "listener-home";
        } else if (usersService.getActiveUser().getUserRole().equals(BasicUser.UserRole.ARTIST)) {
            return "artist-home";
        }
        return "admin-home";
    }
}
