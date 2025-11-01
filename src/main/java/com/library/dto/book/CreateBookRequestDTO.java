package com.library.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequestDTO {
    private String title;
    private String description;
    private Double price;
    private Integer pages;
    private String language;
}
