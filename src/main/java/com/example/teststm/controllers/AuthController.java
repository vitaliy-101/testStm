package com.example.teststm.controllers;

import com.example.teststm.dtos.auth.LoginRequest;
import com.example.teststm.dtos.auth.SignUpRequest;
import com.example.teststm.exceptions.ExistsByLoginException;
import com.example.teststm.services.AuthenticationService;
import com.example.teststm.security.JwtAuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация", description = "Параметры не должны быть пустыми. Admin выдается пользователю с такими данными: " +
        "login: admin; password: admin")
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя", description = "Например : " +
            "login: hero123; " +
            "password: 223daeedpassword; " +
            "fio: Иванов Иван Алексеевич")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) throws ExistsByLoginException {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя", description = "Например : " +
            "login: hero123; " +
            "password: 223daeedpassword")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid LoginRequest request) throws AuthenticationException {
        return authenticationService.signIn(request);
    }
}
