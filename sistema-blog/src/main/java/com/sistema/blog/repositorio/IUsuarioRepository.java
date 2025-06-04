package com.sistema.blog.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.blog.entidades.UsuarioEntity;

public interface IUsuarioRepository extends  JpaRepository<UsuarioEntity, Long>{

	public Optional<UsuarioEntity> findByEmail(String email);
	
	public Optional<UsuarioEntity> findByUsernameOrEmail(String username, String email);
	
	public Optional<UsuarioEntity> findByUsername(String username);
	
	public Boolean existsByUsername(String username);
	
	public Boolean existsByEmail(String email);
}
