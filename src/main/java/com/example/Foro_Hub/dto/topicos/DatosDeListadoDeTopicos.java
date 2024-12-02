package com.example.Foro_Hub.dto.topicos;

import com.example.Foro_Hub.models.Topico;

import java.time.LocalDateTime;

public record DatosDeListadoDeTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion

) {
    public DatosDeListadoDeTopicos(String mensaje, LocalDateTime now, Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                mensaje,
                now
        );
    }

    public DatosDeListadoDeTopicos(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion()
        );
    }
}
