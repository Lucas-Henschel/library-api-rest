package com.library.authConfig;

import com.auth0.jwt.exceptions.JWTCreationException;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.library.controller.exceptions.StandardError;
import com.library.dto.auth.CurrentUserDTO;
import com.library.entities.UserEntity;
import com.library.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private CurrentUserAuthentication currentUserAuthentication;

    @Autowired
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("token");

        if (tokenHeader != null) {
            try {
                Long userId = tokenService.validateToken(tokenHeader);
                UserEntity userEntity = userService.findById(userId);

                CurrentUserDTO currentUserEntityAuthentication = new CurrentUserDTO(
                    userEntity.getId(),
                    userEntity.getName(),
                    userEntity.getLogin(),
                    userEntity.getPassword()
                );

                currentUserAuthentication.setCurrentUserEntity(currentUserEntityAuthentication);
                SecurityContextHolder.getContext().setAuthentication(currentUserAuthentication);
            } catch (JWTCreationException | ResponseStatusException exception) {
                HttpStatus status = HttpStatus.UNAUTHORIZED;
                List<String> errors = List.of("Sessão expirada");

                StandardError err = new StandardError(
                    Instant.now(),
                    status.value(),
                    errors,
                    "Sessão expirada",
                    request.getRequestURI()
                );

                response.setStatus(status.value());
                response.setContentType("application/json");
                response.getWriter().write(objectMapper.writeValueAsString(err));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
