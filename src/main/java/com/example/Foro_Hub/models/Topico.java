package com.example.Foro_Hub.models;

import com.example.Foro_Hub.enums.StatusTopico;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topico")
@Entity(name = "Topico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"curso", "usuario" })
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "titulo", nullable = false, unique = true)
    private String titulo;

    @NotNull
    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NO_RESPONDIDO;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario usuario;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Respuesta> respuestas = new ArrayList<>();


    public Topico(String titulo, String mensaje, LocalDateTime now, StatusTopico
            statusTopico, Usuario usuario, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = now;
        this.status = statusTopico;
        this.usuario = usuario;
        this.curso = curso;
    }

    public void setFechaEdicion(LocalDateTime now) {
        this.fechaCreacion = now;
    }
}
