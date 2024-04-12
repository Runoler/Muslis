package com.example.muslis.controllers;

import com.example.muslis.models.Artist;
import com.example.muslis.models.BasicUser;
import com.example.muslis.models.Listener;
import com.example.muslis.services.ArtistsService;
import com.example.muslis.services.ListenersService;
import com.example.muslis.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

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

        List<BasicUser.Role> roles = Arrays.asList(BasicUser.Role.LISTENER, BasicUser.Role.ARTIST);
        model.addAttribute("Role", BasicUser.Role.values());
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
        if (usersService.getActiveUser().getRole().equals(BasicUser.Role.LISTENER)) {
            return "listener-home";
        } else if (usersService.getActiveUser().getRole().equals(BasicUser.Role.ARTIST)) {
            return "artist-home";
        }
        return "admin-home";
    }
}
