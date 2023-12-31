package com.alura.controller;

import com.alura.modelo.curso.Curso;
import com.alura.modelo.curso.CursoRepository;
import com.alura.modelo.respuesta.RespuestaRepository;
import com.alura.modelo.topico.*;
import com.alura.modelo.usuario.Usuario;
import com.alura.modelo.usuario.UsuarioRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private RegistrarTopicoService registrarTopicoService;

    @Autowired
    TopicoRespository topicoRespository;

    @Autowired
    RespuestaRepository respuestaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos , UriComponentsBuilder uriComponentsBuilder){

        DatosRespuestaTopico datosRespuesta = registrarTopicoService.registrarTopico(datos);

        URI uri = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(datosRespuesta.id()).toUri();

        return ResponseEntity.created(uri).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listadoTopicos(Pageable paginacion){
        return ResponseEntity.ok(topicoRespository.findAll(paginacion).map(DatosListadoTopicos::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarDatoTopico(@PathVariable Long id){
        Topico topico = topicoRespository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),topico.getStatus(),topico.getAutor().getNombre(),topico.getCurso().getNombre());
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos){
        Topico topico = topicoRespository.getReferenceById(datos.topicoId());
        DatosRespuestaTopico datosRespuestaTopico = registrarTopicoService.actualizarTopico(new DatosRegistroTopico(datos.titulo() , datos.mensaje(), topico.getAutor().getId() ,topico.getCurso().getId()), topico);
        return ResponseEntity.ok(datosRespuestaTopico);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity cerrarTopico(@PathVariable Long id){
        Topico topico = topicoRespository.getReferenceById(id);
        topico.cerrarTopico();
        return ResponseEntity.noContent().build();
    }

}
