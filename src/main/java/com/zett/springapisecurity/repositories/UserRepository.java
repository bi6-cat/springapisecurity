package com.zett.springapisecurity.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.springapisecurity.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);

    Boolean existsByUsername(String username);
}