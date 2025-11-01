package com.library.authConfig;

import com.library.dto.auth.CurrentUserDTO;
import com.library.entities.UserEntity;
import com.library.services.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CurrentUserAuthentication currentUserAuthentication;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = (String) authentication.getCredentials();

        Optional<UserEntity> userEntity = userService.findByLogin(login);

        if (userEntity.isPresent()){
            boolean isPasswordValid = passwordEncoder.matches(password, userEntity.get().getPassword());

            if (isPasswordValid){
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

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
