package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/", "/index"})
    public String index(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            model.addAttribute("name", principal.getAttribute("name"));
            model.addAttribute("email", principal.getAttribute("email"));
            return "home"; // show home.html if logged in
        }
        return "index"; // show login page
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal == null) {
            return "redirect:/";
        }

        String email = principal.getAttribute("email");
        if (email == null) {
            Object login = principal.getAttribute("login");
            if (login != null) {
                email = login + "@github.local";
            }
        }

        Optional<User> u = userRepository.findByEmail(email);
        u.ifPresent(user -> model.addAttribute("user", user));

        model.addAttribute("name", principal.getAttribute("name") != null ?
                principal.getAttribute("name") : principal.getAttribute("login"));
        model.addAttribute("email", email);
        model.addAttribute("avatarUrl", principal.getAttribute("picture") != null ?
                principal.getAttribute("picture") : principal.getAttribute("avatar_url"));

        return "dashboard";
    }
}
