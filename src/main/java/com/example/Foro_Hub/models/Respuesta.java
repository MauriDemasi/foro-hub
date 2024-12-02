package com.example.Foro_Hub.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "respuestas")
@Entity (name = "Respuesta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "mensaje", nullable = false, length = 800)
    private String mensaje;




    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime  fechaCreacion = LocalDateTime.now();

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    @JsonManagedReference
    private Topico topico;

    @NotNull
    private Boolean solucion = false;


}
