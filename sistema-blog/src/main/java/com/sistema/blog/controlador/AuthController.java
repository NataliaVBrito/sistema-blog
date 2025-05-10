package com.sistema.blog.controlador;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.DTO.LoginDTO;
import com.sistema.blog.DTO.RegistroDTO;
import com.sistema.blog.entidades.RolEntity;
import com.sistema.blog.entidades.UsuarioEntity;
import com.sistema.blog.repositorio.IRolRepository;
import com.sistema.blog.repositorio.IUsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IRolRepository rolRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseEntity<>("Inicio de sesión exitoso ", HttpStatus.OK);
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
		if(usuarioRepository.existsByUsername(registroDTO.getUsername())) {
			return new ResponseEntity<>("El usuario ya existe", HttpStatus.BAD_REQUEST);
		}
		if(usuarioRepository.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<>("El email ya se encuentra registrado", HttpStatus.BAD_REQUEST);
		}
		
		UsuarioEntity usuario = new UsuarioEntity();
		
		usuario.setNombre(registroDTO.getNombre());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		
		RolEntity roles = rolRepository.findByRol("ROLE_ADMIN").get();
		
		usuario.setRoles(Collections.singleton(roles));
		
		usuarioRepository.save(usuario);
		return new ResponseEntity<>("Te has registrado exitosamente", HttpStatus.OK);
	}
}
