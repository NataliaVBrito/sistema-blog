package com.sistema.blog.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sistema.blog.DTO.ComentarioDTO;
import com.sistema.blog.entidades.ComentarioEntity;
import com.sistema.blog.entidades.PublicacionEntity;
import com.sistema.blog.excepciones.BlogAppException;
import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.sistema.blog.repositorio.IComentarioRepository;
import com.sistema.blog.repositorio.IPublicacionRepository;

@Service
public class ComentarioServiceImp implements IComentarioService {

	@Autowired
	private IComentarioRepository comentarioRepository;

	@Autowired
	private IPublicacionRepository publicacionRepository;

	@Override
	public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO) {
		ComentarioEntity comentarioEntity = mapearEntity(comentarioDTO);
		PublicacionEntity publicacionEntity = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		comentarioEntity.setPublicacion(publicacionEntity);
		ComentarioEntity nuevoComentario = comentarioRepository.save(comentarioEntity);

		return mapearDTO(nuevoComentario);
	}

	private ComentarioDTO mapearDTO(ComentarioEntity comentario) {
		ComentarioDTO comentarioDTO = new ComentarioDTO();

		comentarioDTO.setId(comentario.getId());
		comentarioDTO.setNombre(comentario.getNombre());
		comentarioDTO.setEmail(comentario.getEmail());
		comentarioDTO.setCuerpo(comentario.getCuerpo());

		return comentarioDTO;

	}

	private ComentarioEntity mapearEntity(ComentarioDTO comentario) {
		ComentarioEntity comentarioEntity = new ComentarioEntity();

		comentarioEntity.setId(comentario.getId());
		comentarioEntity.setNombre(comentario.getNombre());
		comentarioEntity.setEmail(comentario.getEmail());
		comentarioEntity.setCuerpo(comentario.getCuerpo());

		return comentarioEntity;
	}

	@Override
	public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long publicacionId) {
		List<ComentarioEntity> comentarios = comentarioRepository.findByPublicacionId(publicacionId);

		return comentarios.stream().map(comentario -> mapearDTO(comentario)).collect(Collectors.toList());
	}

	@Override
	public ComentarioDTO obtenerComentarioPorId(long publicacionId, long comentarioId) {
		PublicacionEntity publicacionEntity = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		ComentarioEntity comentarioEntity = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		if (!comentarioEntity.getPublicacion().getId().equals(publicacionEntity)) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
		}

		return mapearDTO(comentarioEntity);
	}

	@Override
	public ComentarioDTO actualizarComentario(long publicacionId, long comentarioId,
			ComentarioDTO solicitudComentario) {
		PublicacionEntity publicacionEntity = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		ComentarioEntity comentarioEntity = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		if (!comentarioEntity.getPublicacion().getId().equals(publicacionEntity)) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
		}

		comentarioEntity.setNombre(solicitudComentario.getNombre());
		comentarioEntity.setEmail(solicitudComentario.getEmail());
		comentarioEntity.setCuerpo(solicitudComentario.getCuerpo());

		ComentarioEntity comentarioActualizado = comentarioRepository.save(comentarioEntity);

		return mapearDTO(comentarioActualizado);
	}

	@Override
	public void eliminarComentario(long publicacionId, long comentarioId) {

		PublicacionEntity publicacionEntity = publicacionRepository.findById(publicacionId)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));

		ComentarioEntity comentarioEntity = comentarioRepository.findById(comentarioId)
				.orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));

		if (!comentarioEntity.getPublicacion().getId().equals(publicacionEntity)) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicación");
		}

		comentarioRepository.delete(comentarioEntity);

	}

}
