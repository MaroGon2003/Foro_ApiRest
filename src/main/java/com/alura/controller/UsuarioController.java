package com.alura.controller;


import com.alura.modelo.usuario.DatosRegistroUsuario;
import com.alura.modelo.usuario.DatosRespuestaUsuario;
import com.alura.modelo.usuario.Usuario;
import com.alura.modelo.usuario.UsuarioRespository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos){
        Usuario usuario = usuarioRespository.save(new Usuario(datos.nombre(),datos.email(), passwordEncoder.encode(datos.contrasena())));

        DatosRespuestaUsuario datosRespuesta = new DatosRespuestaUsuario(usuario);
        return ResponseEntity.ok(datosRespuesta);
    }

}
