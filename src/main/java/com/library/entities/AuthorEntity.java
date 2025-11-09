package com.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false)
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 45, message = "Nome pode ter no máximo 45 caracteres")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate birthDate;

    @Column(length = 60)
    @Size(max = 60, message = "Nacionalidade pode ter no máximo 60 caracteres")
    private String nationality;

    @Column(columnDefinition = "TEXT")
    @Size(max = 2000, message = "Biografia pode ter no máximo 2000 caracteres")
    private String biography;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
