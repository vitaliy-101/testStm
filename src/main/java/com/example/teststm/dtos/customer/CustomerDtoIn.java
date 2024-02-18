package com.example.teststm.dtos.customer;

import com.example.teststm.entities.Ticket;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerDtoIn extends CustomerBaseDto{
    @NotBlank(message = "password must not be empty")
    private String password;
    @NotBlank(message = "fio must not be empty")
    private String fio;
}
