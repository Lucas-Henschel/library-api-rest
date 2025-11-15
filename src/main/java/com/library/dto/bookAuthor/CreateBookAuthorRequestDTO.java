package com.library.dto.bookAuthor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookAuthorRequestDTO {
    @NotNull
    @Positive
    private Long bookId;
    @NotNull
    @Positive
    private Long authorId;
}
