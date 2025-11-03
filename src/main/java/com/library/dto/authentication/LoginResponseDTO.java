package com.library.dto.authentication;

import com.library.dto.auth.CurrentUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private String token;
    private CurrentUserDTO currentUser;
}
