package com.library.dto.bookAuthor;

import com.library.dto.author.AuthorResponseDTO;
import com.library.dto.book.BookResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAuthorResponseDTO {
    private Long id;
    private BookResponseDTO book;
    private AuthorResponseDTO author;
}
