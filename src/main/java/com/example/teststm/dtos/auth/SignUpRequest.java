package com.example.teststm.dtos.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest extends LoginRequest{
    @NotBlank(message = "fio must not be empty")
    private String fio;
}
