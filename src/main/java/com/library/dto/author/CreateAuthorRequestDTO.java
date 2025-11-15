package com.library.dto.author;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String birthDate;
    @NotBlank
    private String nationality;
    @NotBlank
    private String biography;
}
