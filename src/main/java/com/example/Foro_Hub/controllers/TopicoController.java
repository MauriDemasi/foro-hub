package com.example.Foro_Hub.controllers;

import com.example.Foro_Hub.dto.topicos.DatoDeTopicoIndividual;
import com.example.Foro_Hub.dto.topicos.DatosActualizacionTopico;
import com.example.Foro_Hub.dto.topicos.DatosDeListadoDeTopicos;
import com.example.Foro_Hub.dto.topicos.DatosCreacionTopico;
import com.example.Foro_Hub.enums.StatusTopico;
import com.example.Foro_Hub.models.Usuario;
import com.example.Foro_Hub.models.Curso;
import com.example.Foro_Hub.models.Topico;
import com.example.Foro_Hub.repositories.CursoRepository;
import com.example.Foro_Hub.repositories.TopicoRepository;
import com.example.Foro_Hub.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDeListadoDeTopicos> crearTopico(@RequestBody DatosCreacionTopico datosCreacionTopico) {

       Usuario usuario = usuarioRepository.findById(datosCreacionTopico.autor_id()).orElseThrow(()
               -> new RuntimeException("Usuario no encontrado"));

        Curso curso = cursoRepository.findById(datosCreacionTopico.curso_id()).orElseThrow(()
                -> new RuntimeException("Curso no encontrado"));

        Topico topico = new Topico(
                datosCreacionTopico.titulo(),
                datosCreacionTopico.mensaje(),
                LocalDateTime.now(),
                StatusTopico.valueOf(datosCreacionTopico.status()),
                usuario,
                curso
        );
        topicoRepository.save(topico);

        DatosDeListadoDeTopicos datosDeSalidaCreacionTopico = new DatosDeListadoDeTopicos(
                datosCreacionTopico.mensaje(),
                LocalDateTime.now(),
                topico
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(datosDeSalidaCreacionTopico);
    }


    @GetMapping
  public Page<DatosDeListadoDeTopicos> listarTopicos(@PageableDefault(size = 10 , sort = "fechaCreacion", page = 2) Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosDeListadoDeTopicos::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatoDeTopicoIndividual> obtenerTopicoPorId(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        //validar que el id no sea nulo
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

       Topico  topico = topicoRepository.getReferenceById(id);
       return ResponseEntity.ok(new DatoDeTopicoIndividual(
               topico.getTitulo(),
               topico.getMensaje(),
               topico.getFechaCreacion(),
               StatusTopico.valueOf(String.valueOf(topico.getStatus())),
               topico.getUsuario().getNombre(),
               topico.getCurso().getNombre()
       ));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatoDeTopicoIndividual> actualizarTopico(@PathVariable Long id,
                                                                   @RequestBody DatosActualizacionTopico datosActualizacionTopico) {
        //Con un objeto de la clase Optional  y el metodo isPresent() podemos saber si el objeto existe o no
        Optional <Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.setTitulo(datosActualizacionTopico.titulo());
            topico.setMensaje(datosActualizacionTopico.mensaje());
            topico.setStatus(StatusTopico.valueOf(datosActualizacionTopico.status()));
            topico.setFechaEdicion(LocalDateTime.now());
            topicoRepository.save(topico);
            return ResponseEntity.ok(new DatoDeTopicoIndividual(
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaCreacion(),
                    StatusTopico.valueOf(String.valueOf(topico.getStatus())),
                    topico.getUsuario().getNombre(),
                    topico.getCurso().getNombre()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }



    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
       Optional <Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
