package com.library.mapper;

import com.library.dto.user.UserResponseDTO;
import com.library.entities.UserEntity;
import com.library.helpers.DateHelper;

public class UserMapper {
    public static UserResponseDTO toDTO(UserEntity entity) {
        if (entity == null) return null;

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());

        if (entity.getCreatedAt() != null) {
            dto.setCreatedAt(DateHelper.toIso8601(entity.getCreatedAt()));
        }

        return dto;
    }
}
