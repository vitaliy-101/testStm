package com.example.teststm.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundByIdException extends BadRequestException {
    public <T> NotFoundByIdException(Class<T> clazz, Integer id){
        super(clazz.getSimpleName() + " with id = " + id + " not found");
    }
}
