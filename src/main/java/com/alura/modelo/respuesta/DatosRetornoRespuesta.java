package com.alura.modelo.respuesta;

public record DatosRetornoRespuesta(Long id, String mensaje,  Long topicoId, Long autorId, Boolean solucion) {
    public DatosRetornoRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getId(),respuesta.getAutor().getId(),respuesta.getSolucion());
    }
}
