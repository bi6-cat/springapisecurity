package com.zett.springapisecurity.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zett.springapisecurity.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category findByName(String name);

}
