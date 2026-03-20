package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.UserProfileDTO;
import com.openclassrooms.mddapi.dto.UserRequestDTO;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@Data
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder encoder;

    public UserProfileDTO findUserProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id " + id));
        return userMapper.toDTO(user);
    }

    public UserProfileDTO updateUserProfile(Long id, UserRequestDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id " + id));

        User updatedUser = userMapper.toEntity(userDTO);
        updatedUser.setId(user.getId());
        updatedUser.setPassword(encoder.encode(updatedUser.getPassword()));
        User savedUser = userRepository.save(updatedUser);
        return userMapper.toDTO(savedUser);
    }

}
