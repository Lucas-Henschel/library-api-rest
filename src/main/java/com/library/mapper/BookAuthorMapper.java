package com.library.mapper;

import com.library.dto.bookAuthor.BookAuthorResponseDTO;
import com.library.entities.BookAuthorEntity;

public class BookAuthorMapper {
    public static BookAuthorResponseDTO toDTO(BookAuthorEntity entity) {
        if (entity == null) return null;

        BookAuthorResponseDTO dto = new BookAuthorResponseDTO();
        dto.setId(entity.getId());
        dto.setAuthor(AuthorMapper.toDTO(entity.getAuthor()));
        dto.setBook(BookMapper.toDTO(entity.getBook()));

        return dto;
    }
}
