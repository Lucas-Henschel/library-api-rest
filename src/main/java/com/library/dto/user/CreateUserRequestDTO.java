package com.library.dto.user;

import lombok.Data;

@Data
public class CreateUserRequestDTO {
    private String name;
    private String login;
    private String password;
}
