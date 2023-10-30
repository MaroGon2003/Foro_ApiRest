package com.alura.modelo.respuesta;

import com.alura.modelo.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta,Long> {
}
