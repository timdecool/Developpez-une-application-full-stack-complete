package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.LoginDTO;
import com.openclassrooms.mddapi.dto.TokenDTO;
import com.openclassrooms.mddapi.dto.UserProfileDTO;
import com.openclassrooms.mddapi.dto.UserRequestDTO;
import com.openclassrooms.mddapi.exception.InvalidCredentialsException;
import com.openclassrooms.mddapi.exception.NotUniqueException;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder encoder;

    public TokenDTO login(@Valid LoginDTO login) {
        return generateToken(login.getLogin(), login.getPassword());
    }

    public TokenDTO register(@Valid UserRequestDTO newUser)  {
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new NotUniqueException("Username already exists");
        }
        if(userRepository.existsByEmail(newUser.getEmail())) {
            throw new NotUniqueException("Email already exists");
        }

        User user = userMapper.toEntity(newUser);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return generateToken(newUser.getEmail(), newUser.getPassword());
    }

    /**
     * Generates a token from given credentials using Authentication class from Spring Security.
     * @param login: user login
     * @param password: user password
     * @return TokenDTO
     */
    public TokenDTO generateToken(String login, String password) {
        Authentication authentication;
        try {
            authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login,
                            password
                    )
            );
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new TokenDTO(jwtUtil.generateToken(userDetails.getUsername()));
    }

    public UserProfileDTO getCurrentUserDetails() {
        User user = userRepository.findByEmail(getCurrentUser());
        return userMapper.toDTO(user);
    }

    public static String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

