package com.alura.controller;

import com.alura.modelo.curso.Curso;
import com.alura.modelo.curso.CursoRepository;
import com.alura.modelo.curso.DatosRegistroCurso;
import com.alura.modelo.curso.DatosRespuestaCurso;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarCurso(@RequestBody @Valid DatosRegistroCurso datos){

        Curso curso = cursoRepository.save(new Curso(datos));

        DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(curso);

        return ResponseEntity.ok(datosRespuestaCurso);

    }

}
