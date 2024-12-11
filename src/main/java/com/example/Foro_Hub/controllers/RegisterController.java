package com.example.Foro_Hub.controllers;

import com.example.Foro_Hub.dto.usuario.DatosRegistroUsuario;
import com.example.Foro_Hub.models.Usuario;
import com.example.Foro_Hub.repositories.PerfilRepository;
import com.example.Foro_Hub.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {


    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder  passwordEncoder;

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        try {
            if (usuarioRepository.existsByEmail(datosRegistroUsuario.email())) {
                return ResponseEntity.badRequest().body("El correo electrónico ya está registrado");
            }

            var perfil = perfilRepository.findById(1L).orElseThrow(()-> new RuntimeException("Perfil Inexsitente"));

            Usuario usuario = new Usuario(datosRegistroUsuario);
            usuario.setPerfil(perfil);
            usuario.setPassword(passwordEncoder.encode(datosRegistroUsuario.password()));
            usuarioRepository.save(usuario);

            return ResponseEntity.ok("Usuario registrado exitosamente");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el usuario: " + e.getMessage());
        }

    }



}
