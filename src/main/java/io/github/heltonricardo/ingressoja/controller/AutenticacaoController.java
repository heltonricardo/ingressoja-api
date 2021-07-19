package io.github.heltonricardo.ingressoja.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.heltonricardo.ingressoja.model.Administrador;
import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.model.Produtora;
import io.github.heltonricardo.ingressoja.service.AdministradorService;
import io.github.heltonricardo.ingressoja.service.CompradorService;
import io.github.heltonricardo.ingressoja.service.ProdutoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {

  @Autowired
  private AdministradorService administradorService;
  @Autowired
  private CompradorService compradorService;
  @Autowired
  private ProdutoraService produtoraService;

  @PostMapping
  public ResponseEntity<Map<String, Long>> logar(@RequestBody ObjectNode obj) {

    String email, senha;

    try {
      email = obj.get("email").asText();
      senha = obj.get("senha").asText();
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    Map<String, Long> resposta = new HashMap<>();

    Optional<Comprador> user1 = compradorService.obterPorEmail(email);

    if (user1.isPresent()) {
      Comprador comprador = user1.get();
      if (comprador.getUsuario().getSenha().equals(senha)) {
        resposta.put("id", comprador.getId());
        resposta.put("tipo", 1L);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
      } else
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    Optional<Produtora> user2 = produtoraService.obterPorEmail(email);
    if (user2.isPresent()) {
      Produtora produtora = user2.get();
      if (produtora.getUsuario().getSenha().equals(senha)) {
        resposta.put("id", produtora.getId());
        resposta.put("tipo", 2L);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
      } else
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    Optional<Administrador> user3 = administradorService.obterPorEmail(email);
    if (user3.isPresent()) {
      Administrador administrador = user3.get();
      if (administrador.getUsuario().getSenha().equals(senha)) {
        resposta.put("id", administrador.getId());
        resposta.put("tipo", 3L);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
      } else
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }
}
