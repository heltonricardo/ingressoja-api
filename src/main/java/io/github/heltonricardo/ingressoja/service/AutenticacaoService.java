package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AutenticacaoService {

  private final UsuarioService usuarioService;

  @Autowired
  public AutenticacaoService(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  public Map<String, Long> logar(String email, String senha) {

    Map<String, Long> resposta = new HashMap<>();

    Optional<Usuario> usuarioPesq = usuarioService.obterPorEmail(email);

    if (usuarioPesq.isPresent()) {

      Usuario usuario = usuarioPesq.get();

      if (usuario.getSenha().equals(senha)) {

        resposta.put("id", usuario.getId());

        if (usuario.getComprador() != null)
          resposta.put("tipo", 1L);

        else if (usuario.getProdutora() != null)
          resposta.put("tipo", 2L);

        else if (usuario.getAdministrador() != null)
          resposta.put("tipo", 3L);

        return resposta;
      }
    }

    return null;
  }
}