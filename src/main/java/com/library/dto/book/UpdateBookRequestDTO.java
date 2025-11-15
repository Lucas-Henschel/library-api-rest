package com.library.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequestDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    @Positive
    private Double price;
    @NotNull
    @Positive
    private Integer pages;
    @NotBlank
    private String language;
}
