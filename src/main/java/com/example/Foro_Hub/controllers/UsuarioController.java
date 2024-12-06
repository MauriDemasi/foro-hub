package com.example.Foro_Hub.controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Foro_Hub.dto.usuario.DatosDeUsuarioRegistrado;
import com.example.Foro_Hub.repositories.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

   @GetMapping
   public ResponseEntity<List<DatosDeUsuarioRegistrado>> obtenerUsuarios(@AuthenticationPrincipal UserDetails userDetails) {
      
      if (userDetails == null) {
         throw new RuntimeException("No se encontró el usuario autenticado");
      }

      var usuario = usuarioRepository.findByEmail(userDetails.getUsername());
      if (usuario == null) {
         throw new RuntimeException("No se encontró el usuario autenticado");
      }
      var usuarios = usuarioRepository.findAll();
      var usuariosRegistrados = usuarios.stream()
              .map(usuario1 -> new DatosDeUsuarioRegistrado(
                      usuario1.getNombre(),
                      usuario1.getEmail()
              ))
              .collect(Collectors.toList());

      return new ResponseEntity<>(usuariosRegistrados, HttpStatus.OK);
   }

   //TODO!Get individual para mostrar toda la info de un perfil (Incluso Topicos y Respuestas)
}