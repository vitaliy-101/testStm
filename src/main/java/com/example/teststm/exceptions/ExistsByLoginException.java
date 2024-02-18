package com.example.teststm.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

public class ExistsByLoginException extends BadRequestException {
    public <T> ExistsByLoginException(Class<T> clazz, String login){
        super(clazz.getSimpleName() + " with login = " + login + " already exist");
    }
}
