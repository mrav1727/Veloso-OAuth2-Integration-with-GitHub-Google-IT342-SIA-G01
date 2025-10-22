/*package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            model.addAttribute("name", principal.getAttribute("name") != null ? principal.getAttribute("name") : principal.getAttribute("login"));
            model.addAttribute("email", principal.getAttribute("email"));
            model.addAttribute("avatarUrl", principal.getAttribute("picture") != null ? principal.getAttribute("picture") : principal.getAttribute("avatar_url"));
        }
        return "dashboard";
    }
}
*/