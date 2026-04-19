package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.LoginDTO;
import com.openclassrooms.mddapi.dto.TokenDTO;
import com.openclassrooms.mddapi.dto.UserProfileDTO;
import com.openclassrooms.mddapi.dto.UserRequestDTO;
import com.openclassrooms.mddapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST controller handling authentication operations
 * Public endpoints : POST /auth/login, POST /auth/register
 * Protected endpoint: GET /auth/me
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * Authenticates a user and returns user data and a JWT Token.
     *
     * @param login the user credentials, username or email and password
     * @return 200 with TokenDTO on success
     *         400 if the request body is invalid
     *         500 if the credentials are incorrect
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO login) {
        return ResponseEntity.ok(authService.login(login));
    }

    /**
     * Registers a new user and return user data and a JWT Token
     *
     * @param user user data: username, email, password
     * @return 200 with TokenDTO on success
     *         400 if the request body is invalid or fails validation
     */
    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@Valid @RequestBody UserRequestDTO user) {
        return ResponseEntity.ok(authService.register(user));
    }

    /**
     * Returns the account data of the currently authenticated user
     *
     * @return 200 with UserProfileDTO
     *         401 if the JWT token is missing or invalid
     */
    @GetMapping("/me")
    public ResponseEntity<UserProfileDTO> me() {
        return ResponseEntity.ok(authService.getCurrentUserDetails());
    }
}
