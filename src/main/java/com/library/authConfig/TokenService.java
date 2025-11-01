package com.library.authConfig;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.library.dto.auth.CurrentUserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(CurrentUserDTO CurrentUserEntity) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                .withIssuer("library-api")
                .withSubject(CurrentUserEntity.getId().toString())
                .withExpiresAt(getExpirationDate())
                .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generation token" , exception);
        }
    }

    public Long validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String userId = JWT.require(algorithm)
                .withIssuer("library-api")
                .build()
                .verify(token)
                .getSubject();

            return Long.parseLong(userId);
        } catch (TokenExpiredException | JWTCreationException exception) {
            throw new JWTCreationException("Sessão expirada", exception);
        } catch (JWTDecodeException exception) {
            throw new JWTDecodeException("Token invalido", exception);
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusMinutes(5).toInstant(ZoneOffset.of("-03:00"));
    }
}
