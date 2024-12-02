package com.example.Foro_Hub.infra.security;
import com.example.Foro_Hub.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    private  TokenService tokenService;

    @Autowired
    private UsuarioRepository  usuarioRepository;

    //Rutas que no pasan por el filtro para poder loguearse
    private  final List <String> publicRoutes = List.of("/login", "/register");



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null ) {
           filterChain.doFilter(request, response);
            return;
        }

        if (authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");
            String subject = tokenService.getSubject(token);
            if (subject != null) {
                var usuario = usuarioRepository.findByEmail(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);

    }

}

