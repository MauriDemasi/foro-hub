package com.example.Foro_Hub.repositories;

import com.example.Foro_Hub.models.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    UserDetails findByEmail(String userDetails);

    List<Usuario> findAll();

    Usuario findByNombreAndActivoTrue(String nombre);

    boolean existsByEmail(@NotNull @Email String email);

    Usuario findByEmailAndActivoTrue(String nombre);

}
