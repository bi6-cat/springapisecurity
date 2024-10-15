package com.zett.springapisecurity.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", length = 100, nullable = false, columnDefinition = "Nvarchar(100)")
    private String name;

    @Column(name = "description", columnDefinition = "Nvarchar(255)")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_active")
    private boolean isActive;
}
