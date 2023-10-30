package com.alura.modelo.topico;

import com.alura.modelo.curso.Curso;
import com.alura.modelo.respuesta.Respuesta;
import com.alura.modelo.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "topicos")
@Entity(name = "Topico")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	private String mensaje;

	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autor_id")
	private Usuario autor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id")
	private Curso curso;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "topico_id", referencedColumnName = "id")
	private List<Respuesta> respuestas = new ArrayList<>();

	public Topico(DatosRegistroTopico datos, Usuario autor, Curso curso) {
		this.titulo = datos.titulo();
		this.mensaje = datos.mensaje();
		this.autor = autor;
		this.curso = curso;
	}

	public void agregarRespuesta(Respuesta respuesta){

		respuestas.add(respuesta);

		if (respuesta.getSolucion()){
			this.status = StatusTopico.SOLUCIONADO;
		}else{
			this.status = StatusTopico.NO_SOLUCIONADO;
		}

	}
	public void cerrarTopico() {
		this.status = StatusTopico.CERRADO;
	}

	public void setEstado(StatusTopico status) {
		this.status = status;
	}

}
