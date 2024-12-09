package com.example.Foro_Hub.services;


import com.example.Foro_Hub.dto.respuestas.DatosDeRespuestasParaMostrar;
import com.example.Foro_Hub.dto.topicos.DatoDeTopicoIndividual;
import com.example.Foro_Hub.dto.usuario.DatosDePerfilCompleto;
import com.example.Foro_Hub.models.Usuario;
import com.example.Foro_Hub.repositories.RespuestaRespository;
import com.example.Foro_Hub.repositories.TopicoRepository;
import com.example.Foro_Hub.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository  topicoRepository;

    @Autowired
    private RespuestaRespository  respuestaRespository;

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


}
