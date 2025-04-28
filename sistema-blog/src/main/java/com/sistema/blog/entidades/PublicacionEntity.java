package com.sistema.blog.entidades;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "publicaciones", uniqueConstraints = { @UniqueConstraint(columnNames = { "titulo" }) })
public class PublicacionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String descripcion;

	@Column(nullable = false)
	private String contenido;

	@JsonBackReference
	@OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ComentarioEntity> comentarios = new HashSet<>();

	public PublicacionEntity() {
		super();
	}

	public PublicacionEntity(Long id, String titulo, String descripcion, String contenido) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}

	public PublicacionEntity(Long id, String titulo, String descripcion, String contenido,
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
