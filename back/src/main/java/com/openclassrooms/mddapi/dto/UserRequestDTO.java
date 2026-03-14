package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotNull(message="Username is required")
    private String username;

    @NotNull(message="Login is required")
    @NotBlank(message="Login cannot be blank")
    private String email;

    @NotNull(message="Password is required")
    @NotBlank(message="Password cannot be blank")
    private String password;

}
