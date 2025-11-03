package com.library.dto.bookAuthor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookAuthorRequestDTO {
    private Long bookId;
    private Long authorId;
}
