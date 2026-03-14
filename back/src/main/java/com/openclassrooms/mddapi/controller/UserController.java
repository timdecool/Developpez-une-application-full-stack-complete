package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserProfileDTO;
import com.openclassrooms.mddapi.dto.UserRequestDTO;
import com.openclassrooms.mddapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> findProfile(@PathVariable("id") final Long id) {
        UserProfileDTO user = userService.findUserProfile(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> updateProfile(
            @PathVariable("id") final Long id,
            @Valid @RequestBody UserRequestDTO dto
    ) {
        UserProfileDTO userProfile = userService.updateUserProfile(id, dto);
        return ResponseEntity.ok(userProfile);
    }

    @PostMapping("")
    public ResponseEntity<UserProfileDTO> createUser(
            @Valid @RequestBody UserRequestDTO dto
    ) {
        UserProfileDTO userProfile = userService.createUser(dto);
        return ResponseEntity.ok(userProfile);
    }

}
