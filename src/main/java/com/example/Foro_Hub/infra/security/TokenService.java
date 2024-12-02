package com.example.Foro_Hub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.Foro_Hub.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String  generateToken(Usuario usuario) {
       try {
           Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.create()
                   .withIssuer("Foro-hub")
                   .withSubject(usuario.getEmail())
                   .withClaim("id", usuario.getId())
                   .withExpiresAt(getExpirationTime())
                   .sign(algorithm);
       }catch (JWTCreationException e) {
           throw new RuntimeException("Error al generar el token", e);
       }
    }

    public String getSubject(String token) {

        if (token == null) {
            throw new RuntimeException("Token no válido");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Foro-hub")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTCreationException e) {
            throw new RuntimeException("Invalid token", e);
        }
    }

    private Instant getExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
