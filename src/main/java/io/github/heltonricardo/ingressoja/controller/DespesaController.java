package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.model.Despesa;
import io.github.heltonricardo.ingressoja.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

  private final DespesaService despesaService;

  @Autowired
  public DespesaController(
      DespesaService despesaService) {

    this.despesaService = despesaService;
  }

  /********************************* EXCLUIR **********************************/

  @DeleteMapping("/{id}")
  public ResponseEntity<?> excluir(@PathVariable Long id) {

    Optional<Despesa> pesq = despesaService.obterPorId(id);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    despesaService.excluir(pesq.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
