package com.example.Foro_Hub.dto.topicos;

import com.example.Foro_Hub.enums.StatusTopico;
import com.example.Foro_Hub.models.Curso;
import com.example.Foro_Hub.models.Usuario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatoDeTopicoIndividual(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String autor,
        String curso
) {


    public DatoDeTopicoIndividual(@NotNull String titulo, @NotNull String mensaje, @NotNull
    LocalDateTime fechaCreacion, @NotNull StatusTopico status, @NotNull String nombre) {

        this(titulo, mensaje, fechaCreacion, status, nombre, null);
    }
}
