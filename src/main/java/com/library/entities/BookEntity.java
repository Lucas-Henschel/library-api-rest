package com.library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false)
    @NotBlank(message = "O título não pode estar vazio")
    @Size(max = 45, message = "O título pode ter no máximo 45 caracteres")
    private String title;

    @Column(length = 45)
    @Size(max = 45, message = "A descrição pode ter no máximo 45 caracteres")
    private String description;

    @Column(nullable = false, precision = 10)
    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
    private Double price;

    @Column(nullable = false)
    @NotNull(message = "O número de páginas é obrigatório")
    @Min(value = 1, message = "O livro deve ter pelo menos 1 página")
    private Integer pages;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "O idioma é obrigatório")
    @Size(max = 30, message = "O idioma pode ter no máximo 30 caracteres")
    private String language;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
