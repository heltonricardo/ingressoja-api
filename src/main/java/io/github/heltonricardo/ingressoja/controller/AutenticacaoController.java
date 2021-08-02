package io.github.heltonricardo.ingressoja.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.heltonricardo.ingressoja.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {

  private final AutenticacaoService autenticacaoService;

  @Autowired
  public AutenticacaoController(AutenticacaoService autenticacaoService) {
    this.autenticacaoService = autenticacaoService;
  }

  @PostMapping
  public ResponseEntity<Map<String, Long>> logar(@RequestBody ObjectNode obj) {

    try {
      String email = obj.get("email").asText();
      String senha = obj.get("senha").asText();
      Map<String, Long> resposta = autenticacaoService.logar(email, senha);

      if (resposta == null)
        throw new Exception();

      return new ResponseEntity<>(resposta, HttpStatus.OK);
    } //
    catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }
}
