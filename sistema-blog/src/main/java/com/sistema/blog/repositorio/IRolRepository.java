package com.sistema.blog.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.entidades.RolEntity;
import com.sistema.blog.entidades.UsuarioEntity;

public interface IRolRepository extends JpaRepository<RolEntity, Long> {
	
	public Optional<RolEntity> findByRol(String rol);

}
