package com.library.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAuthorRequestDTO {
    private String name;
    private String birthDate;
    private String nationality;
    private String biography;
}
