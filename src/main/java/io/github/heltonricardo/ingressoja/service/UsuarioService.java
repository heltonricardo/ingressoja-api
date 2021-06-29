package io.github.heltonricardo.ingressoja.service;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.heltonricardo.ingressoja.model.Usuario;
import io.github.heltonricardo.ingressoja.repository.UsuarioRepository;

public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
