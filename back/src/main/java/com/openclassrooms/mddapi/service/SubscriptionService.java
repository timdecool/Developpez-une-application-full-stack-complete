package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserProfileDTO;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    public void subscribe(Long themeId) {
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new NoSuchElementException("Theme not found with id " + themeId));
        User user = userRepository.findByEmail(AuthService.getCurrentUser());

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setTheme(theme);
        subscriptionRepository.save(subscription);
    }

    public void unsubscribe(Long themeId) {
        User user = userRepository.findByEmail(AuthService.getCurrentUser());
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new NoSuchElementException("Theme not found with id " + themeId));

        Subscription subscription = subscriptionRepository.findByThemeAndUser(theme, user)
                .orElseThrow(() -> new NoSuchElementException("Subscription not found"));
        subscriptionRepository.delete(subscription);
    }

}
