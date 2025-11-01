package com.library.mapper;

import com.library.dto.user.BookResponseDTO;
import com.library.entities.BookEntity;
import com.library.helpers.DateHelper;

public class BookMapper {
    public static BookResponseDTO toDTO(BookEntity entity) {
        if (entity == null) return null;

        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setPages(entity.getPages());
        dto.setLanguage(entity.getLanguage());

        if (entity.getCreatedAt() != null) {
            dto.setCreatedAt(DateHelper.toIso8601(entity.getCreatedAt()));
        }

        return dto;
    }
}
