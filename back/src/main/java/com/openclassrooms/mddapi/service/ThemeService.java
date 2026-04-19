package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Service handling business logic for theme management.
 * Provides operations for retrieving themes.
 */
@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ThemeMapper themeMapper;

    /**
     * Retrieves all themes while checking for authenticated user subscription status for each of them.
     *
     * @return a list of themes as data transfer objects ThemeDTO
     * @throws NoSuchElementException if user is not found
     */
    public List<ThemeDTO> findAllThemes() {
        User user = userRepository.findByEmail(AuthService.getCurrentUser()).orElseThrow(
                () -> new NoSuchElementException("User not found with email " + AuthService.getCurrentUser())
        );

        return themeRepository.findAll()
                .stream()
                .map(theme -> {
                    ThemeDTO dto = themeMapper.toDTO(theme);
                    dto.setSubscribed(
                            subscriptionRepository.findByThemeAndUser(theme, user)
                                    .isPresent()
                    );
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * Retrieves all the authenticated user subscribed themes.
     *
     * @return a list of themes as data transfer objects ThemeDTO
     * @throws NoSuchElementException if user is not found
     */
    public List<ThemeDTO> findMyThemes() {
        User user = userRepository.findByEmail(AuthService.getCurrentUser()).orElseThrow(
                () -> new NoSuchElementException("User not found with email " + AuthService.getCurrentUser())
        );
        return themeRepository.findSubscribedThemesByUserId(user.getId())
                .stream()
                .map(theme -> {
                    ThemeDTO dto = themeMapper.toDTO(theme);
                    dto.setSubscribed(true);
                    return dto;
                }).collect(Collectors.toList());
    }
}
