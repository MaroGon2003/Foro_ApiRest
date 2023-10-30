package com.alura.modelo.topico;

import java.time.LocalDateTime;

public record DatosListadoTopicos(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, StatusTopico status, String autor, String curso ){

    public DatosListadoTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),topico.getAutor().getNombre(),topico.getCurso().getNombre());
    }
}
