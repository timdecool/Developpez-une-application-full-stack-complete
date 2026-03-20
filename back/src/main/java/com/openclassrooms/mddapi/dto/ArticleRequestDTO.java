package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequestDTO {

    @NotNull(message="Article title is required")
    @NotBlank(message="Article title cannot be blank")
    private String title;

    @NotNull(message="Article content is required")
    @NotBlank(message="Article content cannot be blank")
    private String content;

    @NotNull(message="Theme id is required")
    private Long themeId;

    @NotNull(message="User id is required")
    private Long userId;

}
