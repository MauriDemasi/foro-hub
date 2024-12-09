package com.example.Foro_Hub.dto.respuestas;

import com.example.Foro_Hub.models.Curso;
import com.example.Foro_Hub.models.Topico;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosDeRespuestasParaMostrar(
    String mensaje,
    LocalDateTime fechaCreacion,
    Boolean solucion,
    Topico topico,
    Curso curso
) {
    public DatosDeRespuestasParaMostrar(@NotNull String mensaje, @NotNull LocalDateTime fechaCreacion,
                                        @NotNull String titulo) {

        this(mensaje, fechaCreacion, false, new Topico(titulo), null);
    }
}
