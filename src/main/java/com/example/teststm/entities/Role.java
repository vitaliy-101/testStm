package com.example.teststm.entities;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role  {
    ROLE_USER,
    ROLE_ADMIN
}