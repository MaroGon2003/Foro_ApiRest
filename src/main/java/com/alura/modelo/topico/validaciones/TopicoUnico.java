package com.alura.modelo.topico.validaciones;

import com.alura.modelo.topico.DatosRegistroTopico;
import com.alura.modelo.topico.TopicoRespository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoUnico implements ValidadorDeTopicos{

    @Autowired
    private TopicoRespository topicoRespository;

    @Override
    public void validar(DatosRegistroTopico datos) {

        if(datos.titulo() == null || datos.mensaje()== null){
            return;
        }

        var topicoDuplicado = topicoRespository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());

        if (topicoDuplicado){
            throw new ValidationException("El topico que ingreso ya esta posteado");
        }

    }
}
