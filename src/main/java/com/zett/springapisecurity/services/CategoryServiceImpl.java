package com.zett.springapisecurity.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zett.springapisecurity.dtos.category.CategoryCreateUpdateDTO;
import com.zett.springapisecurity.dtos.category.CategoryDTO;
import com.zett.springapisecurity.entities.Category;
import com.zett.springapisecurity.repositories.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository _categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this._categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        var categories = _categoryRepository.findAll();

        var CategoryDTOs = categories.stream().map(category -> {
            var categoryDTO = new CategoryDTO();
            categoryDTO.setName(category.getName());
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setActive(category.isActive());
            return categoryDTO;
        }).toList();

        return CategoryDTOs;
    }

    @Override
    public CategoryDTO findById(UUID id) {
        var category = _categoryRepository.findById(id).orElse(null);

        if(category == null) {
            throw new IllegalArgumentException("Category with id " + id + " does not exist");
        }

        var categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setActive(category.isActive());

        return categoryDTO;
    }

    @Override
    public CategoryDTO findByName(String name) {
        var category = _categoryRepository.findByName(name);

        var categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setActive(category.isActive());

        return categoryDTO;
    }

    @Override
    public CategoryDTO create(CategoryCreateUpdateDTO categoryDTO) {
        var existingCategory = _categoryRepository.findByName(categoryDTO.getName());

        if(existingCategory != null) {
            throw new IllegalArgumentException("Category with name " + categoryDTO.getName() + " already exists");
        }

        var category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setCreatedAt(LocalDateTime.now());
        category.setActive(categoryDTO.isActive());

        var createdCategory = _categoryRepository.save(category);

        var createdCategoryDTO = new CategoryDTO();
        createdCategoryDTO.setName(createdCategory.getName());
        createdCategoryDTO.setDescription(createdCategory.getDescription());
        createdCategoryDTO.setActive(createdCategory.isActive());

        return createdCategoryDTO;
    }

    @Override
    public CategoryDTO update(UUID id, CategoryCreateUpdateDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new IllegalArgumentException("Category is required");
        }

        var existingCategory = _categoryRepository.findById(id).orElse(null);

        if(existingCategory == null) {
            throw new IllegalArgumentException("Category with id " + id + " does not exist");
        }

        var existedCategory = _categoryRepository.findByName(categoryDTO.getName());

        if (existedCategory != null && !existedCategory.getId().equals(id)) {
            throw new IllegalArgumentException("Category name is existed");
        }

        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());
        existingCategory.setUpdatedAt(LocalDateTime.now());
        existingCategory.setActive(categoryDTO.isActive());

        var updatedCategory = _categoryRepository.save(existingCategory);

        var updatedCategoryDTO = new CategoryDTO();
        updatedCategoryDTO.setId(updatedCategory.getId());
        updatedCategoryDTO.setName(updatedCategory.getName());
        updatedCategoryDTO.setDescription(updatedCategory.getDescription());

        return updatedCategoryDTO;
    }

    @Override
    public boolean delete(UUID id) {
        var existingCategory = _categoryRepository.findById(id).orElse(null);

        if(existingCategory == null) {
            throw new IllegalArgumentException("Category with id " + id + " does not exist");
        }

        _categoryRepository.delete(existingCategory);

        var isDeleted = _categoryRepository.findById(id).isEmpty();

        return isDeleted;
    }

    @Override
    public boolean delete(UUID id, boolean isSoftDelete) {
        var existingCategory = _categoryRepository.findById(id).orElse(null);

        if (existingCategory == null) {
            throw new IllegalArgumentException("Category not found");
        }

        if (isSoftDelete) {
            existingCategory.setDeleted(true);
            existingCategory.setDeletedAt(LocalDateTime.now());
            _categoryRepository.save(existingCategory);

            // Check if category entity is soft deleted
            var deletedCategory = _categoryRepository.findById(id).orElse(null);
            if (deletedCategory != null && deletedCategory.isDeleted()) {
                return true;
            } else {
                return false;
            }
        } else {
            
            _categoryRepository.delete(existingCategory);
            // Check if category entity is deleted
            var isDeleted = _categoryRepository.findById(id).isEmpty();

            return isDeleted;
        }
    }
}
