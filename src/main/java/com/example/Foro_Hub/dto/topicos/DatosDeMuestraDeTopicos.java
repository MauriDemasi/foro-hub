package com.example.Foro_Hub.dto.topicos;

import com.example.Foro_Hub.models.Topico;

import java.time.LocalDateTime;

public record DatosDeMuestraDeTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion
) {

    public DatosDeMuestraDeTopicos(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion()
        );
    }
}
