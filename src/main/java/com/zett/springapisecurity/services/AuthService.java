package com.zett.springapisecurity.services;

import java.util.UUID;

import com.zett.springapisecurity.dtos.auth.RegisterRequestDTO;

public interface AuthService {
    // Register
    UUID register(RegisterRequestDTO registerRequestDTO);

    boolean existsByUsername(String username);
}
