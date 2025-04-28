package com.sistema.blog.DTO;

import java.util.List;

public class PublicacionResponse {

	private List<PublicacionDTO> contenido;
	private int pageNum;
	private int pageSize;
	private long totalElementos;
	private int totalPaginas;
	private boolean ultima;

	public PublicacionResponse() {
		super();
	}

	public PublicacionResponse(List<PublicacionDTO> contenido, int pageNum, int pageSize, long totalElementos,
			int totalPaginas, boolean ultima) {
		super();
		this.contenido = contenido;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalElementos = totalElementos;
		this.totalPaginas = totalPaginas;
		this.ultima = ultima;
	}

	public List<PublicacionDTO> getContenido() {
		return contenido;
	}

	public void setContenido(List<PublicacionDTO> contenido) {
		this.contenido = contenido;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalElementos() {
		return totalElementos;
	}

	public void setTotalElementos(long totalElementos) {
		this.totalElementos = totalElementos;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}

	public boolean isUltima() {
		return ultima;
	}

	public void setUltima(boolean ultima) {
		this.ultima = ultima;
	}

}
