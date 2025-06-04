package com.sistema.blog.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.entidades.ComentarioEntity;

public interface IComentarioRepository extends JpaRepository<ComentarioEntity, Long>{

	public List<ComentarioEntity> findByPublicacionId(long publicacionId);
}
