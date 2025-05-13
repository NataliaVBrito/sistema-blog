package com.sistema.blog.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.blog.entidades.RolEntity;
import com.sistema.blog.entidades.UsuarioEntity;
import com.sistema.blog.repositorio.IUsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		UsuarioEntity usuario = usuarioRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException(
						"Usuario no encontrado con el username o el email ingresado : " + usernameOrEmail));
		
		return new User(usuario.getEmail(), usuario.getPassword(), mapearRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearRoles(Set<RolEntity> roles) {
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getRol())).collect(Collectors.toList());
	}

}
