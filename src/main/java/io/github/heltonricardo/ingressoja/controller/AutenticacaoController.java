package io.github.heltonricardo.ingressoja.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.heltonricardo.ingressoja.model.Usuario;
import io.github.heltonricardo.ingressoja.service.AdministradorService;
import io.github.heltonricardo.ingressoja.service.CompradorService;
import io.github.heltonricardo.ingressoja.service.ProdutoraService;
import io.github.heltonricardo.ingressoja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {

  private final AdministradorService administradorService;
  private final CompradorService compradorService;
  private final ProdutoraService produtoraService;
  private final UsuarioService usuarioService;

  @Autowired
  public AutenticacaoController(AdministradorService administradorService,
                                CompradorService compradorService,
                                ProdutoraService produtoraService,
                                UsuarioService usuarioService) {
    this.administradorService = administradorService;
    this.compradorService = compradorService;
    this.produtoraService = produtoraService;
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public ResponseEntity<Map<String, Long>> logar(@RequestBody ObjectNode obj) {

    String email, senha;

    try {
      email = obj.get("email").asText();
      senha = obj.get("senha").asText();
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    Map<String, Long> resposta = new HashMap<>();

    Optional<Usuario> usuarioPesq = usuarioService.obterPorEmail(email);

    if (usuarioPesq.isPresent()) {
      Usuario usuario = usuarioPesq.get();
      if (usuario.getSenha().equals(senha)) {

        System.out.println(usuario.getAdministrador());
        System.out.println(usuario.getComprador());
        System.out.println(usuario.getProdutora());



        resposta.put("id", usuario.getId());
        resposta.put("tipo", 1L);




        return new ResponseEntity<>(resposta, HttpStatus.OK);
      } else
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }
}
