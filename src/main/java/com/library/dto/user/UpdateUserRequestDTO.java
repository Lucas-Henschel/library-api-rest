package com.library.dto.user;

import lombok.Data;

@Data
public class UpdateUserRequestDTO {
    private String name;
    private String login;
    private String password;
}
