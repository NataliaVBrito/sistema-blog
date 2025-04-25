package com.sistema.blog.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.blog.DTO.PublicacionDTO;
import com.sistema.blog.DTO.PublicacionResponse;
import com.sistema.blog.entidades.PublicacionEntity;
import com.sistema.blog.excepciones.ResourceNotFoundException;
import com.sistema.blog.repositorio.IPublicacionRepository;

@Service
public class PublicacionServiceImp implements IPublicacionService {

	@Autowired
	private IPublicacionRepository publicacionRepositorio;

	@Override
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
		PublicacionEntity publicacion = mapearEntidad(publicacionDTO);
		PublicacionEntity nuevaPublicacion = publicacionRepositorio.save(publicacion);
		PublicacionDTO publicacionRespuesta = mapearDTO(nuevaPublicacion);

		return publicacionRespuesta;
	}

	@Override
	public PublicacionResponse obtenerPublicaciones(int numPagina, int pageSize) {
		Pageable pageable = PageRequest.of(numPagina, pageSize);
		Page<PublicacionEntity> paginacion = publicacionRepositorio.findAll(pageable);
		
		List<PublicacionEntity> publicaciones = paginacion.getContent();
		List<PublicacionDTO> contenido = publicaciones.stream().map(publicacion -> mapearDTO(publicacion)).collect(Collectors.toList());
		
		PublicacionResponse publicacionResponse = new PublicacionResponse();
		publicacionResponse.setContenido(contenido);
		publicacionResponse.setPageNum(numPagina);
		publicacionResponse.setPageSize(pageSize);
		publicacionResponse.setTotalElementos(paginacion.getTotalElements());
		publicacionResponse.setTotalPaginas(paginacion.getTotalPages());
		publicacionResponse.setUltima(paginacion.isLast());
		
		return publicacionResponse;
	}

	private PublicacionDTO mapearDTO(PublicacionEntity publicacion) {
		PublicacionDTO publicacionDTO = new PublicacionDTO();

		publicacionDTO.setId(publicacion.getId());
		publicacionDTO.setTitulo(publicacion.getTitulo());
		publicacionDTO.setDescripcion(publicacion.getDescripcion());
		publicacionDTO.setContenido(publicacion.getContenido());

		return publicacionDTO;
	}

	private PublicacionEntity mapearEntidad(PublicacionDTO publicacionDTO) {
		PublicacionEntity publicacion = new PublicacionEntity();

		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());

		return publicacion;
	}

	@Override
	public PublicacionDTO obtenerPublicacionPorId(long id) {
		PublicacionEntity publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

		return mapearDTO(publicacion);
	}

	@Override
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
		PublicacionEntity publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

		publicacion.setTitulo(publicacionDTO.getTitulo());
		publicacion.setDescripcion(publicacionDTO.getDescripcion());
		publicacion.setContenido(publicacionDTO.getContenido());

		PublicacionEntity publicacionActualizada = publicacionRepositorio.save(publicacion);

		return mapearDTO(publicacionActualizada);
	}

	@Override
	public void eliminarPublicacion(long id) {
		PublicacionEntity publicacion = publicacionRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));

		publicacionRepositorio.delete(publicacion);
	}
}
