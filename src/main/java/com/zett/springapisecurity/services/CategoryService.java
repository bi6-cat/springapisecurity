package com.zett.springapisecurity.services;

import java.util.List;
import java.util.UUID;

import com.zett.springapisecurity.dtos.category.CategoryCreateUpdateDTO;
import com.zett.springapisecurity.dtos.category.CategoryDTO;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(UUID id);

    CategoryDTO findByName(String name);

    CategoryDTO create(CategoryCreateUpdateDTO categoryDTO);

    CategoryDTO update(UUID id, CategoryCreateUpdateDTO categoryDTO);
    
    boolean delete(UUID id);

    boolean delete(UUID id, boolean isSoftDelete);
}
