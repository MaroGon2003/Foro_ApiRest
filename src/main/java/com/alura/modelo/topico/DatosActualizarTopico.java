package com.alura.modelo.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long topicoId, String titulo, String mensaje) {

}
