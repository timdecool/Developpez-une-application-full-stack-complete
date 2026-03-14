package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.dto.UserProfileDTO;
import com.openclassrooms.mddapi.dto.UserRequestDTO;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserMapper userMapper;

    public UserProfileDTO findUserProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return userMapper.toDTO(user);
    }

    public UserProfileDTO updateUserProfile(Long id, UserRequestDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        User updatedUser = userMapper.toEntity(userDTO);
        updatedUser.setId(user.getId());
        User savedUser = userRepository.save(updatedUser);
        return userMapper.toDTO(savedUser);
    }

    public UserProfileDTO createUser(UserRequestDTO dto) {
        User newUser = userMapper.toEntity(dto);
        User savedUser = userRepository.save(newUser);
        return userMapper.toDTO(savedUser);
    }
}
