package com.example.teststm.services;

import com.example.teststm.dtos.auth.LoginRequest;
import com.example.teststm.dtos.auth.SignUpRequest;
import com.example.teststm.entities.Customer;
import com.example.teststm.entities.Role;
import com.example.teststm.exceptions.ExistsByLoginException;
import com.example.teststm.security.JwtAuthenticationResponse;
import com.example.teststm.security.JwtService;
import com.example.teststm.security.UserDetailsImpl;
import com.example.teststm.security.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    public JwtAuthenticationResponse signUp(SignUpRequest request) throws ExistsByLoginException {
        Customer customer = new Customer(request.getLogin(), passwordEncoder.encode(request.getPassword()), request.getFio());
        customer.setRole(Role.ROLE_USER);
        if(customer.getLogin().equals("admin") && request.getPassword().equals("admin")){
            customer.setRole(Role.ROLE_ADMIN);
        }
        customerService.createCustomer(customer);
        UserDetailsImpl user = UserDetailsImpl.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .fio(request.getFio())
                .role(customer.getRole())
                .build();
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
    public JwtAuthenticationResponse signIn(LoginRequest request) throws AuthenticationException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getLogin(),
                    request.getPassword()
            ));
        }
        catch (RuntimeException e){
            throw new AuthenticationException("incorrect login or password");
        }


        var user = userService.loadUserByUsername(request.getLogin());
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}

