package com.example.Foro_Hub.dto.topicos;

import java.time.LocalDateTime;

public record DatosActualizacionTopico(
        Long autor_id,
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        Long curso_id
) {}