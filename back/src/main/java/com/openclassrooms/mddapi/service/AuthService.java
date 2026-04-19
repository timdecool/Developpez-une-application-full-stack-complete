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
import java.util.NoSuchElementException;

/**
 * Service handling authentication logic and token generation.
 * Provides operations for registering an account, logging and retrieving user details.
 */
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

    /**
     * Generates a token based on given credentials.
     *
     * @param login user credentials as data transfer object LoginDTO
     * @return user token and data as data transfer object TokenDTO
     */
    public TokenDTO login(@Valid LoginDTO login) {
        return generateToken(login.getLogin(), login.getPassword());
    }

    /**
     * Creates a user account and generates a token based on account data.
     *
     * @param newUser user data as data transfer object UserRequestDTO containing username, email and password.
     * @return user token and data as data transfer object TokenDTO
     * @throws NotUniqueException if given username or email is already associated to an account.
     */
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
     * Updates a user account and generates a token based on account data.
     *
     * @param id the user account identifier
     * @param userDTO user account data as data transfer object UserRequestDTO
     * @return user token and data as data transfer object TokenDTO
     * @throws NoSuchElementException if user is not found
     */
    public TokenDTO updateUser(Long id, UserRequestDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id " + id));

        User updatedUser = userMapper.toEntity(userDTO);
        updatedUser.setId(user.getId());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            updatedUser.setPassword(encoder.encode(updatedUser.getPassword()));
        }
        else {
            updatedUser.setPassword(user.getPassword());
        }

        User savedUser = userRepository.save(updatedUser);

        TokenDTO token = new TokenDTO();
        token.setToken(jwtUtil.generateToken(savedUser.getEmail()));
        token.setUser(userMapper.toDTO(savedUser));
        return token;
    }

    /**
     * Generates a token from given credentials using Authentication class from Spring Security.
     * @param login: user login
     * @param password: user password
     * @return user token and data as data transfer object TokenDTO
     * @throws InvalidCredentialsException if login and password do not match
     * @throws NoSuchElementException if user is not found based on its email
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
        TokenDTO token = new TokenDTO();
        token.setToken(jwtUtil.generateToken(userDetails.getUsername()));

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new NoSuchElementException("User not found with email " + userDetails.getUsername())
        );
        token.setUser(userMapper.toDTO(user));

        return token;
    }

    /**
     * Retrieves user data based on authenticated user email
     * @return user data as data transfer object UserProfileDTO
     * @throws NoSuchElementException if user is not found
     */
    public UserProfileDTO getCurrentUserDetails() {
        User user = userRepository.findByEmail(getCurrentUser()).orElseThrow(
                () -> new NoSuchElementException("User not found with email " + getCurrentUser())
        );
        return userMapper.toDTO(user);
    }

    /**
     * Retrieves user email based on token attached to the currently handled request.
     * @return user email
     */
    public static String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

