package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.utils.Pagamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste")
public class Test {

  @GetMapping
  public ResponseEntity<String> pagamento() {

    String resp = Pagamento.gerarUrlPagamento(null);

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }
}
