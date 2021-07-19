package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto_in.ProdutoraDTO;
import io.github.heltonricardo.ingressoja.dto_out.ProdutoraDTOResp;
import io.github.heltonricardo.ingressoja.model.Produtora;
import io.github.heltonricardo.ingressoja.service.ProdutoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("produtora")
public class ProdutoraController {

  private final ProdutoraService produtoraService;

  @Autowired
  public ProdutoraController(ProdutoraService produtoraService) {

    this.produtoraService = produtoraService;
  }

  /******************************* OBTER TODAS ********************************/

  @GetMapping
  public ResponseEntity<List<ProdutoraDTOResp>> obterTodas() {

    List<ProdutoraDTOResp> resp = new ArrayList<>();

    produtoraService.obterTodas()
        .forEach(c -> resp.add(ProdutoraDTOResp.paraDTO(c)));

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /******************************* OBTER POR ID *******************************/

  @GetMapping("/{id}")
  public ResponseEntity<ProdutoraDTOResp> obterPorId(@PathVariable Long id) {

    Optional<Produtora> pesq = produtoraService.obterPorId(id);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    ProdutoraDTOResp resp = ProdutoraDTOResp.paraDTO(pesq.get());

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<ProdutoraDTOResp> salvar(
      @RequestBody ProdutoraDTO dto) {

    Produtora resp = produtoraService.salvar(dto.paraObjeto());

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.CONFLICT);

    return new ResponseEntity<>(ProdutoraDTOResp.paraDTO(resp),
        HttpStatus.CREATED);
  }
}