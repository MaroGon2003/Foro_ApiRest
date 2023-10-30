package com.alura.modelo.usuario;

import com.alura.modelo.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRespository extends JpaRepository<Usuario,Long> {
    UserDetails findByEmail(String username);

}
