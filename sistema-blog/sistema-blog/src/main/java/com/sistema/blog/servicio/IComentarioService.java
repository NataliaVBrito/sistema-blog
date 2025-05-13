package com.sistema.blog.servicio;

import java.util.List;

import com.sistema.blog.DTO.ComentarioDTO;

public interface IComentarioService {

	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO);
	
	public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long publicacionId);
	
	public ComentarioDTO obtenerComentarioPorId(long publicacionId, long comentarioId);
	
	public ComentarioDTO actualizarComentario(long publicacionId, long comentarioId, ComentarioDTO solicitudComentario);
	
	public void eliminarComentario(long publicacionId, long comentarioId);
}
