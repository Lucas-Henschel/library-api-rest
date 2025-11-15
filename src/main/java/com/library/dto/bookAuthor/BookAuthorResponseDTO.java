package com.library.dto.bookAuthor;

import com.library.dto.author.AuthorResponseDTO;
import com.library.dto.book.BookResponseDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthorResponseDTO {
    @NotBlank
    private Long id;
    @NotBlank
    private BookResponseDTO book;
    @NotBlank
    private AuthorResponseDTO author;
}
