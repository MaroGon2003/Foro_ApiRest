package com.alura.controller;

import com.alura.modelo.respuesta.*;
import com.alura.modelo.topico.DatosListadoTopicos;
import com.alura.modelo.topico.StatusTopico;
import com.alura.modelo.topico.Topico;
import com.alura.modelo.topico.TopicoRespository;
import com.alura.modelo.usuario.Usuario;
import com.alura.modelo.usuario.UsuarioRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController {

    @Autowired
    TopicoRespository topicoRespository;

    @Autowired
    UsuarioRespository usuarioRespository;

    @Autowired
    RespuestaRepository respuestaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRetornoRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos, UriComponentsBuilder uriComponentsBuilder){

        Topico topico = topicoRespository.getReferenceById(datos.topicoId());

        if (topico.getStatus().toString() == "CERRADO" || topico == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Usuario usuario = usuarioRespository.getReferenceById(datos.autorId());

        Respuesta respuesta = respuestaRepository.save(new Respuesta(datos,topico,usuario));

        topico.agregarRespuesta(respuesta);

        DatosRetornoRespuesta datosRetornoRespuesta = new DatosRetornoRespuesta(respuesta);

        URI uri = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(datosRetornoRespuesta.id()).toUri();

        return ResponseEntity.created(uri).body(datosRetornoRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuestas(Pageable paginacion){
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosListadoRespuesta::new));
    }

}
