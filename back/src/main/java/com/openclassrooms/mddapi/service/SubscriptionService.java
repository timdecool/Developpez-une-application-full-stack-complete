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

    public void subscribe(Long userId, Long themeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new NoSuchElementException("Theme not found"));

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setTheme(theme);
        subscriptionRepository.save(subscription);
    }

    public void unsubscribe(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Subscription not found"));

        subscriptionRepository.delete(subscription);
    }

}
