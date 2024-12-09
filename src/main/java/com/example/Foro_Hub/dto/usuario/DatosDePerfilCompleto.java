package com.example.Foro_Hub.dto.usuario;

import com.example.Foro_Hub.dto.respuestas.DatosDeRespuestasParaMostrar;
import com.example.Foro_Hub.dto.topicos.DatoDeTopicoIndividual;
import java.util.List;

public record DatosDePerfilCompleto(
        Long id,
        String nombre,
        String email,
        List <DatoDeTopicoIndividual> topicos,
        List <DatosDeRespuestasParaMostrar> respuestas

) {
}
