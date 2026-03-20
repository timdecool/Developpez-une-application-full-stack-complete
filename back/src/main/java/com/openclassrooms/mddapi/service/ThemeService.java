package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.repository.ThemeRepository;
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
    public ThemeMapper themeMapper;

    public List<ThemeDTO> findAllThemes() {
        return themeRepository.findAll()
                .stream()
                .map(themeMapper::toDTO).collect(Collectors.toList());
    }
}
