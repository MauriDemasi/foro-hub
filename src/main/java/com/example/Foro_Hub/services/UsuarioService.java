package com.example.Foro_Hub.services;


import com.example.Foro_Hub.dto.respuestas.DatosDeRespuestasParaMostrar;
import com.example.Foro_Hub.dto.topicos.DatoDeTopicoIndividual;
import com.example.Foro_Hub.dto.usuario.DatosDePerfilCompleto;
import com.example.Foro_Hub.dto.usuario.DatosParaActualizar;
import com.example.Foro_Hub.models.Usuario;
import com.example.Foro_Hub.repositories.RespuestaRespository;
import com.example.Foro_Hub.repositories.TopicoRepository;
import com.example.Foro_Hub.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository  topicoRepository;

    @Autowired
    private RespuestaRespository  respuestaRespository;

    @Autowired
    private PasswordEncoder  passwordEncoder;

    public Optional<Usuario> findByEmail(String email) {
        return Optional.ofNullable((Usuario) usuarioRepository.findByEmail(email));
    }

    @Transactional
    public DatosDePerfilCompleto obtenerPerfilCompleto (Usuario usuarioId){
        var usuario = usuarioRepository.findById(usuarioId.getId())
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));

        //Obtenemos los topicos del usuario
        List<DatoDeTopicoIndividual> topicos = usuario.getTopicos()
                .stream()
                .map(topico -> new DatoDeTopicoIndividual(
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getCurso().getNombre()
                    ))
                .collect(Collectors.toList());

        //Obtenemos las respuestas del usuario
        List < DatosDeRespuestasParaMostrar  > respuestas = usuario.getRespuestas()
                .stream()
                .map(respuesta -> new DatosDeRespuestasParaMostrar(
                        respuesta.getMensaje(),
                        respuesta.getFechaCreacion(),
                        respuesta.getTopico().getTitulo()
                ))
                .collect(Collectors.toList());

        return new DatosDePerfilCompleto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                topicos,
                respuestas
        );
    }


    @Transactional
    public DatosParaActualizar actualizarUsuario (DatosParaActualizar datosParaActualizar, String nombre){

        Usuario usuario = (Usuario) usuarioRepository.findByEmail(nombre);

        if (usuario == null){
            throw new RuntimeException("Usuario no encontrado");
        }
        
        if (datosParaActualizar.nombre() != null){

            usuario.setNombre(datosParaActualizar.nombre());
        }

        if (datosParaActualizar.email() != null){
            usuario.setEmail(datosParaActualizar.email());
        }

        if (datosParaActualizar.password() != null){
            usuario.setPassword(passwordEncoder.encode(datosParaActualizar.password()));
        }


        return new DatosParaActualizar(
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword()
        );


    }


    @Transactional
    public void darDeBajaUsuario(String username) {
        // Buscar el usuario por su nombre de usuario
        Usuario usuarioAEliminar = usuarioRepository.findByEmailAndActivoTrue(username);

        if (usuarioAEliminar == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        try {
            // Desactivar el usuario
            usuarioAEliminar.setActivo(false);

            // Guardar los cambios en la base de datos
            usuarioRepository.save(usuarioAEliminar);

            // Eliminar los topicos, respuestas y comentarios asociados al usuario
            topicoRepository.deleteAll(usuarioAEliminar.getTopicos());
            respuestaRespository.deleteAll(usuarioAEliminar.getRespuestas());
        } catch (Exception e) {
            throw new RuntimeException("Error al desactivar el usuario: " + e.getMessage());
        }


    }
}
