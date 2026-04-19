package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ThemeDTO;
import com.openclassrooms.mddapi.model.Theme;
import org.springframework.stereotype.Component;

@Component
public class ThemeMapper {

    public ThemeDTO toDTO(Theme theme) {
        ThemeDTO dto = new ThemeDTO();
        dto.setId(theme.getId());
        dto.setName(theme.getName());
        dto.setDescription(theme.getDescription());
        return dto;
    }

}
