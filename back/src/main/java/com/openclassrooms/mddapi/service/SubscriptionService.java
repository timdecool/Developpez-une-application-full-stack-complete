package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Service handling business logic for theme subscription.
 * Provides operations for subscribing and unsubscribing to a theme.
 */
@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    /**
     * Creates a subscription with authenticated user id and given theme.
     *
     * @param themeId theme identifier
     * @throws NoSuchElementException if theme or user is not found
     */
    public void subscribe(Long themeId) {
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new NoSuchElementException("Theme not found with id " + themeId));
        User user = userRepository.findByEmail(AuthService.getCurrentUser()).orElseThrow(
                () -> new NoSuchElementException("User not found with email " + AuthService.getCurrentUser())
        );

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setTheme(theme);
        subscriptionRepository.save(subscription);
    }

    /**
     * Deletes a subscription with authenticated user id and given theme.
     * @param themeId theme identifier
     * @throws NoSuchElementException if user, theme or subscription is not found
     */
    public void unsubscribe(Long themeId) {
        User user = userRepository.findByEmail(AuthService.getCurrentUser()).orElseThrow(
                () -> new NoSuchElementException("User not found with email " + AuthService.getCurrentUser())
        );
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new NoSuchElementException("Theme not found with id " + themeId));

        Subscription subscription = subscriptionRepository.findByThemeAndUser(theme, user)
                .orElseThrow(() -> new NoSuchElementException("Subscription not found"));
        subscriptionRepository.delete(subscription);
    }
}
