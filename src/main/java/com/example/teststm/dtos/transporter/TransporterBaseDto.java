package com.example.teststm.dtos.transporter;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransporterBaseDto {
    @NotBlank(message = "name must not be empty")
    private String name;
}
