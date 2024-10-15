package com.zett.springapisecurity.dtos.category;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private UUID id;

    @NotBlank(message = "Name is required")
    @Length(min = 2, max = 100, message = "Name must be less than 100 characters")
    private String name;

    @Length(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @NotNull(message = "isActive is required")
    private boolean isActive;
}
