package com.example.Foro_Hub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Table(name = "usuario")
@Entity (name = "Usuario")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",  nullable = false)
    private Long id;

    @NotNull
    @Column( name = "nombre", unique =true)
    private String nombre;

    @NotNull
    @Email
    @Column( name = "email", unique =true)
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;


    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    public Usuario(Long autorId) {
        this.id = autorId;
    }
}
