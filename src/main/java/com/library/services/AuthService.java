package com.library.services;

import com.library.authConfig.CurrentUserAuthentication;
import com.library.dto.auth.CurrentUserDTO;
import com.library.entities.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CurrentUserAuthentication currentUserAuthentication;

    public Authentication validLogin(String login, String password) {
        Optional<UserEntity> userEntity = userService.findByLogin(login);

        if (userEntity.isPresent()) {
            boolean isPasswordValid = passwordEncoder.matches(password, userEntity.get().getPassword());

            if (isPasswordValid) {
                CurrentUserDTO currentUserEntityAuthentication = new CurrentUserDTO(
                    userEntity.get().getId(),
                    userEntity.get().getName(),
                    userEntity.get().getLogin(),
                    userEntity.get().getPassword()
                );

                currentUserAuthentication.setCurrentUserEntity(currentUserEntityAuthentication);
                return currentUserAuthentication;
            }
        }

        return null;
    }

    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);

            return true;
        }

        return false;
    }
}
