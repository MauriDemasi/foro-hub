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
    LocalDateTime fechaCreacion, String s, Long id, Long id1) {
        this(titulo, mensaje, fechaCreacion, StatusTopico.valueOf(s), new Usuario(id), new Curso(id1));
    }

    public DatoDeTopicoIndividual(@NotNull String titulo, @NotNull String mensaje,
                                  @NotNull LocalDateTime fechaCreacion, StatusTopico statusTopico, Usuario usuario, Curso curso) {
        this(titulo, mensaje, fechaCreacion, StatusTopico.valueOf(statusTopico.name()), usuario.getNombre(), curso.getNombre());
    }
}
