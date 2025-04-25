package com.sistema.blog.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import com.sistema.blog.entidades.PublicacionEntity;

public interface IPublicacionRepository extends JpaRepository<PublicacionEntity, Long >{
	

}
