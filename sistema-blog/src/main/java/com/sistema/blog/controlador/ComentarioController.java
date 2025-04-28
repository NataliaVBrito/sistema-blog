package com.sistema.blog.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.DTO.ComentarioDTO;
import com.sistema.blog.servicio.IComentarioService;

@RestController
@RequestMapping("/api/publicaciones")
public class ComentarioController {

	@Autowired
	private IComentarioService comentarioService;

	@GetMapping("/{publicacionId}/comentarios")
	public List<ComentarioDTO> listarComentariosPorPublicacion(
			@PathVariable(value = "publicacionId") long publicacionId) {
		return comentarioService.obtenerComentariosPorPublicacionId(publicacionId);
	}

	@GetMapping("/{publicacionId}/comentario/{comentarioId}")
	public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(
			@PathVariable(value = "publicacionId") long publicacionId,
			@PathVariable(value = "comentarioId") long comentarioId) {
		ComentarioDTO comentarioDTO = comentarioService.obtenerComentarioPorId(publicacionId, comentarioId);

		return new ResponseEntity<>(comentarioDTO, HttpStatus.OK);
	}

	@PostMapping("/{publicacionId}/comentarios")
	public ResponseEntity<ComentarioDTO> crearComentario(@PathVariable(value = "publicacionId") long publicacionId,
			@RequestBody ComentarioDTO comentarioDTO) {
		return new ResponseEntity<>(comentarioService.crearComentario(publicacionId, comentarioDTO),
				HttpStatus.CREATED);
	}

	@PutMapping("/{publicacionId}/comentario/{comentarioId}")
	public ResponseEntity<ComentarioDTO> actualizarComentarioPorId(
			@PathVariable(value = "publicacionId") long publicacionId,
			@PathVariable(value = "comentarioId") long comentarioId, @RequestBody ComentarioDTO comentarioDTO) {

		return new ResponseEntity<>(comentarioService.actualizarComentario(publicacionId, comentarioId, comentarioDTO),
				HttpStatus.OK);
	}

	@DeleteMapping("/{publicacionId}/comentario/{comentarioId}")
	public ResponseEntity<String> eliminarComentario(@PathVariable(value = "publicacionId") long publicacionId,
			@PathVariable(value = "comentarioId") long comentarioId) {
		comentarioService.eliminarComentario(publicacionId, comentarioId);

		return new ResponseEntity<>("Comentario eliminado con exito", HttpStatus.OK);
	}

}
