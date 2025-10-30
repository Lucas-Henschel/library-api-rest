package com.library.dto.user;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String login;
    private String password;
    private String createdAt;
}
