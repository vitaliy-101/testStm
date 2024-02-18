package com.example.teststm.dtos.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerBaseDto {

    private String login;
}
