package com.library.mapper;

import com.library.dto.author.AuthorResponseDTO;
import com.library.entities.AuthorEntity;
import com.library.helpers.DateHelper;

public class AuthorMapper {
    public static AuthorResponseDTO toDTO(AuthorEntity entity) {
        if (entity == null) return null;

        AuthorResponseDTO dto = new AuthorResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBirthDate(entity.getBirthDate());
        dto.setNationality(entity.getNationality());
        dto.setBiography(entity.getBiography());

        if (entity.getCreatedAt() != null) {
            dto.setCreatedAt(DateHelper.toIso8601(entity.getCreatedAt()));
        }

        return dto;
    }
}
