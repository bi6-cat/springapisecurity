package com.zett.springapisecurity.dtos.category;


import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateUpdateDTO {
    @NotBlank(message = "Name is required")
    @Length(min = 2, max = 100, message = "Name must be less than 100 characters")
    private String name;

    @Length(max = 255, message = "Description must be less than 255 characters")
    private String description;

    private boolean isActive;

    
}
