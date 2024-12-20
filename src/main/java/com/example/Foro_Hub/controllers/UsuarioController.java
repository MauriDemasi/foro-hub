package com.example.Foro_Hub.controllers;


import com.example.Foro_Hub.dto.topicos.DatoDeTopicoIndividual;
import com.example.Foro_Hub.dto.usuario.DatosDePerfilCompleto;
import com.example.Foro_Hub.dto.usuario.DatosParaActualizar;
import com.example.Foro_Hub.models.Usuario;
import com.example.Foro_Hub.repositories.TopicoRepository;
import com.example.Foro_Hub.services.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
   private TopicoRepository  topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;



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

   //Get individual para mostrar toda la info de un perfil (Incluso Topicos y Respuestas)
   @GetMapping("/perfil")
   public ResponseEntity<List<DatosDePerfilCompleto>> obtenerUsuario(@AuthenticationPrincipal UserDetails userDetails) {

      if (userDetails == null) {
         return ResponseEntity.status(401).build();
      }

      var usuario = usuarioRepository.findByEmail(userDetails.getUsername());

      if (usuario == null) {
         return ResponseEntity.notFound().build();
      }

      DatosDePerfilCompleto perfilCompleto = usuarioService.obtenerPerfilCompleto((Usuario) usuario);
      return ResponseEntity.ok(List.of(perfilCompleto));
   }


   //Put para actualizar la info de un perfil
   @PutMapping
   public ResponseEntity<DatosParaActualizar> actualizarUsuario(@Valid @RequestBody DatosParaActualizar  datosParaActualizar,
                                                                @AuthenticationPrincipal UserDetails userDetails) {

      if (userDetails == null) {
         return ResponseEntity.status(401).build();
      }

      var usuarioActualizado = usuarioService.actualizarUsuario(
             datosParaActualizar, userDetails.getUsername());

      return ResponseEntity.ok(usuarioActualizado);

   }

   //Delete para que un usuario pueda dar de baja su perfil

   @DeleteMapping
   public ResponseEntity<String> darDeBajaUsuario(@AuthenticationPrincipal UserDetails userDetails) {

      if (userDetails == null) {
         return ResponseEntity.status(401).body("Usuario no autenticado");
      }


      try {
         usuarioService.darDeBajaUsuario(userDetails.getUsername());
         return ResponseEntity.ok("Perfil desactivado con éxito.");
      } catch (RuntimeException e) {
         return ResponseEntity.status(404).body(e.getMessage());
      }
   }

   
}