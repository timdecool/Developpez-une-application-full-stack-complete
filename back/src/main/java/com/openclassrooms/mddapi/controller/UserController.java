package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TokenDTO;
import com.openclassrooms.mddapi.dto.UserRequestDTO;
import com.openclassrooms.mddapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST controller handling user management operations.
 * Protected endpoints: PUT /users/{id}
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthService authService;

    /**
     * Updates and returns user account
     *
     * @param id user identifier
     * @param dto UserRequestDTO with username, email and password
     * @return 200 with TokenDTO on success
     *         400 if request body is invalid or fails validation
     *         404 if user is not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<TokenDTO> updateProfile(
            @PathVariable("id") final Long id,
            @Valid @RequestBody UserRequestDTO dto
    ) {
        TokenDTO userProfile = authService.updateUser(id, dto);
        return ResponseEntity.ok(userProfile);
    }
}
