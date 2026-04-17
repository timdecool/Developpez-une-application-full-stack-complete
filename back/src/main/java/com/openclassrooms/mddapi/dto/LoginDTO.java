package com.openclassrooms.mddapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotNull(message="Login is required")
    @NotBlank(message="Login cannot be blank")
    private String login;

    @NotNull(message="Password is required")
    @NotBlank(message="Password cannot be blank")
    private String password;
}