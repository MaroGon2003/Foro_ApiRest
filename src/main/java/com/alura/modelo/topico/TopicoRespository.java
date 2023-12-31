package com.alura.modelo.topico;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicoRespository extends JpaRepository<Topico,Long> {

    Boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
