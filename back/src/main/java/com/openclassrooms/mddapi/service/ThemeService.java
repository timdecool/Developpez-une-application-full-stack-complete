package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ThemeDTO> findAllThemes() {
        User user = userRepository.findByEmail(AuthService.getCurrentUser());

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

    public List<ThemeDTO> findMyThemes() {
        User user = userRepository.findByEmail(AuthService.getCurrentUser());
        return themeRepository.findSubscribedThemesByUserId(user.getId())
                .stream()
                .map(theme -> {
                    ThemeDTO dto = themeMapper.toDTO(theme);
                    dto.setSubscribed(true);
                    return dto;
                }).collect(Collectors.toList());
    }
}
