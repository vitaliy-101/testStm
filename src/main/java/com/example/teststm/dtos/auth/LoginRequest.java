package com.example.teststm.dtos.auth;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "login must not be empty")
    private String login;
    @NotBlank(message = "password must not be empty")
    private String password;
}
