package com.sistema.blog.DTO;

public class ComentarioDTO {

	private long id;
	private String nombre;
	private String email;
	private String cuerpo;

	public ComentarioDTO() {
		super();
	}

	public ComentarioDTO(long id, String nombre, String email, String cuerpo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.cuerpo = cuerpo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

}
