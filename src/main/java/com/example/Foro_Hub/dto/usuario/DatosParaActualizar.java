package com.example.Foro_Hub.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosParaActualizar(
        String nombre,
        String email,
        String password
) {


}
