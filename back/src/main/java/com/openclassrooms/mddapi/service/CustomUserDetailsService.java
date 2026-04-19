package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Service that handles user retrieval security logic.
 * Implements UserDetailsService.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves user based on either its email or username.
     * The returned UuserDetails uses the email as the principal identifier.
     *
     * @param login user email or username
     * @return a Spring Security UserDetails object for the authenticated user
     * @throws NoSuchElementException if not user matches the provided login
     */
    @Override
    public UserDetails loadUserByUsername(String login) {

        User user = userRepository.findByEmail(login)
                .or(() -> userRepository.findByUsername(login))
                .orElseThrow(() -> new NoSuchElementException("User not found with login " + login))
                ;

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
