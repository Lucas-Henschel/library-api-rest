package com.library.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private LocalDateTime birthDate;
    private String nationality;
    private String biography;
    private String createdAt;
}
