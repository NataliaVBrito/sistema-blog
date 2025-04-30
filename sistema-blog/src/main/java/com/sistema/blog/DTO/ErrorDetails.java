package com.sistema.blog.DTO;

import java.util.Date;

public class ErrorDetails {

	private Date marcaDeTiempo;
	private String mensaje;
	private String detalles;

	public ErrorDetails(Date marcaDeTiempo, String mensaje, String detalles) {
		super();
		this.marcaDeTiempo = marcaDeTiempo;
		this.mensaje = mensaje;
		this.detalles = detalles;
	}

	public ErrorDetails() {
		super();
	}

	public Date getMarcaDeTiempo() {
		return marcaDeTiempo;
	}

	public void setMarcaDeTiempo(Date marcaDeTiempo) {
		this.marcaDeTiempo = marcaDeTiempo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

}
