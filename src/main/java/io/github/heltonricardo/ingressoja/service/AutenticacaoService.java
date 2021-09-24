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

        if (usuario.getComprador() != null
            && usuario.getComprador().getAtivo()) {
          resposta.put("id", usuario.getComprador().getId());
          resposta.put("tipo", 1L);
        } //
        else if (usuario.getProdutora() != null
            && usuario.getProdutora().getAtivo()) {
          resposta.put("id", usuario.getProdutora().getId());
          resposta.put("tipo", 2L);
        } //
        else if (usuario.getAdministrador() != null
            && usuario.getProdutora().getAtivo()) {
          resposta.put("id", usuario.getAdministrador().getId());
          resposta.put("tipo", 3L);
        } //
        else
          return null;

        return resposta;
      }
    }

    return null;
  }
}
