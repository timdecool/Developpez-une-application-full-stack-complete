package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDTO {

    @NotNull(message="Comment content is required")
    @NotBlank(message="Comment content cannot be blank")
    private String content;

    @NotNull(message="Article id is required")
    private Long articleId;

    @NotNull(message="User id is required")
    private Long userId;

}
