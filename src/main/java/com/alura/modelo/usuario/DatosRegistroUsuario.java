package com.alura.modelo.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario (
    @NotBlank
    String nombre,
    @NotBlank
    @Email
    String email,
    @NotNull
    String contrasena ){
}
