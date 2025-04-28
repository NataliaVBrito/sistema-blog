package com.sistema.blog.DTO;

import java.util.Set;

import com.sistema.blog.entidades.ComentarioEntity;

public class PublicacionDTO {

	private Long id;
	private String titulo;
	private String descripcion;
	private String contenido;
	private Set<ComentarioEntity> comentarios;

	public PublicacionDTO() {

	}

	public PublicacionDTO(Long id, String titulo, String descripcion, String contenido) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}

	public PublicacionDTO(Long id, String titulo, String descripcion, String contenido,
			Set<ComentarioEntity> comentarios) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.contenido = contenido;
		this.comentarios = comentarios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Set<ComentarioEntity> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<ComentarioEntity> comentarios) {
		this.comentarios = comentarios;
	}

}
