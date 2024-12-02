package com.example.Foro_Hub.dto.usuario;

import com.example.Foro_Hub.models.Perfil;

public record DatosRegistroUsuario(
        String nombre,
        String email,
        String password,
        Perfil  perfil
) {
}
