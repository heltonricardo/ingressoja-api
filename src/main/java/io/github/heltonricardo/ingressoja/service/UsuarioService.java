package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Usuario;
import io.github.heltonricardo.ingressoja.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;

  @Autowired
  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public Optional<Usuario> obterPorId(Long id) {
    return usuarioRepository.findById(id);
  }

  public Optional<Usuario> obterPorEmail(String email) {
    return usuarioRepository.findByEmail(email);
  }
}
