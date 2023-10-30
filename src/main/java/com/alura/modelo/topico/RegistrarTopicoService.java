package com.alura.modelo.topico;

import com.alura.modelo.curso.Curso;
import com.alura.modelo.curso.CursoRepository;
import com.alura.modelo.topico.validaciones.TopicoUnico;
import com.alura.modelo.topico.validaciones.ValidadorDeTopicos;
import com.alura.modelo.usuario.Usuario;
import com.alura.modelo.usuario.UsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrarTopicoService {

    @Autowired
    private TopicoRespository topicoRespository;

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    List<ValidadorDeTopicos> validadores;

    public DatosRespuestaTopico registrarTopico(DatosRegistroTopico datos){

        validadores.forEach(v->v.validar(datos));

        Usuario autor = usuarioRespository.getReferenceById(datos.autorId());
        Curso curso = cursoRepository.getReferenceById(datos.cursoId());

        Topico topico = topicoRespository.save(new Topico(datos,autor,curso));

        return new DatosRespuestaTopico(topico);

    }

}
