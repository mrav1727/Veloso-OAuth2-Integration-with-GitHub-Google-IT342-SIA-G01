package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ProfileController {

    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public String viewProfile(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal == null) {
            return "redirect:/";
        }
        String email = principal.getAttribute("email");
        if (email == null) {
            Object login = principal.getAttribute("login");
            if (login != null) email = login + "@github.local";
        }
        Optional<User> u = userRepository.findByEmail(email);
        if (u.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("user", u.get());
        return "profile"; // create templates/profile.html
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal OAuth2User principal,
                                @RequestParam(value = "displayName", required = false) String displayName,
                                @RequestParam(value = "bio", required = false) String bio) {
        if (principal == null) return "redirect:/";
        String email = principal.getAttribute("email");
        if (email == null) {
            Object login = principal.getAttribute("login");
            if (login != null) email = login + "@github.local";
        }
        Optional<User> u = userRepository.findByEmail(email);
        if (u.isEmpty()) return "redirect:/";
        User user = u.get();
        boolean modified = false;
        if (displayName != null && !displayName.trim().isEmpty() && !displayName.equals(user.getDisplayName())) {
            user.setDisplayName(displayName);
            modified = true;
        }
        if (bio != null && !bio.equals(user.getBio())) {
            user.setBio(bio);
            modified = true;
        }
        if (modified) {
            userRepository.save(user);
        }
        return "redirect:/profile";
    }
}
