package com.example.Foro_Hub.infra.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                    .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF, adecuado para APIs REST
//                    .sessionManagement(session ->
//                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // No mantener estado de sesión
//                    .authorizeHttpRequests(auth -> auth
//                            .requestMatchers(HttpMethod.POST, "/topico").permitAll() // Permitir acceso público a /topico
//                            .anyRequest().authenticated() // Requiere autenticación para las demás rutas
//                    );
//
//            return http.build();
//        }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Deshabilitar CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // Permitir acceso a todas las rutas
                );

        return http.build();
    }


        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

