package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TokenDTO;
import com.openclassrooms.mddapi.dto.UserRequestDTO;
import com.openclassrooms.mddapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthService authService;


    @PutMapping("/{id}")
    public ResponseEntity<TokenDTO> updateProfile(
            @PathVariable("id") final Long id,
            @Valid @RequestBody UserRequestDTO dto
    ) {
        TokenDTO userProfile = authService.updateUserProfile(id, dto);
        return ResponseEntity.ok(userProfile);
    }
}
