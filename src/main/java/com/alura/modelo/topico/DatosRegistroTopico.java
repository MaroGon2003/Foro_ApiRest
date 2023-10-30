package com.alura.modelo.topico;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(

        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        Long autorId,
        @NotNull
        Long cursoId

) {
}
