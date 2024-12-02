package com.example.Foro_Hub.repositories;

import com.example.Foro_Hub.models.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    UserDetails findByEmail(String email);

    boolean existsByEmail(@NotNull @Email String email);

}
