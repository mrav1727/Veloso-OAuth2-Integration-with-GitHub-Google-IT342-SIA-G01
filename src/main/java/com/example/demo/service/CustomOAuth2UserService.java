package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oauth2User.getAttributes();

        String email = null;
        String name = null;
        String avatarUrl = null;

        if ("google".equals(provider)) {
            email = (String) attributes.get("email");
            name = (String) attributes.get("name");
            avatarUrl = (String) attributes.get("picture");
        } else if ("github".equals(provider)) {
            name = (String) attributes.get("name");
            avatarUrl = (String) attributes.get("avatar_url");
            // GitHub may not always return an email
            email = (String) attributes.get("email");
            if (email == null) {
                Object login = attributes.get("login");
                email = login + "@github.local";
            }
        }

        if (email == null) {
            throw new IllegalArgumentException("Email not found for user: " + name);
        }

        final String finalEmail = email;
        final String finalName = name;
        final String finalAvatarUrl = avatarUrl;

        User user = userRepository.findByEmail(finalEmail).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(finalEmail);
            newUser.setName(finalName);
            newUser.setAvatarUrl(finalAvatarUrl);
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(newUser);
        });

        boolean updated = false;
        if (name != null && !name.equals(user.getName())) {
            user.setName(name);
            updated = true;
        }
        if (avatarUrl != null && !avatarUrl.equals(user.getAvatarUrl())) {
            user.setAvatarUrl(avatarUrl);
            updated = true;
        }
        if (updated) {
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
        }

        return oauth2User;
    }
}
