package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByThemeAndUser(Theme theme, User user);
}
