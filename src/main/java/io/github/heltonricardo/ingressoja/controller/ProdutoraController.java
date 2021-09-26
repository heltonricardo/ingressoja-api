package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto_in.ProdutoraDTO;
import io.github.heltonricardo.ingressoja.dto_out.EventoDTORespProdutora;
import io.github.heltonricardo.ingressoja.dto_out.ProdutoraDTOResp;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.Produtora;
import io.github.heltonricardo.ingressoja.service.ProdutoraService;
import io.github.heltonricardo.ingressoja.utils.UsarFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    Optional<Produtora> pesq = produtoraService.obterPorId(id, UsarFiltro.SIM);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    ProdutoraDTOResp resp = ProdutoraDTOResp.paraDTO(pesq.get());

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /****************************** OBTER EVENTOS *******************************/

  @GetMapping("/{id}/eventos")
  public ResponseEntity<List<EventoDTORespProdutora>> obterEventos(
      @PathVariable Long id) {

    List<Evento> eventos = produtoraService.obterEventos(id);

    if (eventos == null)
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    List<EventoDTORespProdutora> resp = new ArrayList<>();

    eventos.forEach(e -> resp.add(EventoDTORespProdutora.paraDTO(e)));

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<ProdutoraDTOResp> salvar(
      @RequestBody @Valid ProdutoraDTO dto) {

    Produtora resp = produtoraService.salvar(dto.paraObjeto());

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.CONFLICT);

    return new ResponseEntity<>(ProdutoraDTOResp.paraDTO(resp),
        HttpStatus.CREATED);
  }

  /******************************** ATUALIZAR *********************************/

  @PutMapping("/{id}")
  public ResponseEntity<ProdutoraDTOResp> atualizar(
      @RequestBody @Valid ProdutoraDTO dto, @PathVariable Long id) {

    Produtora resp = produtoraService.atualizar(dto.paraObjeto(), id);

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.CONFLICT);

    return new ResponseEntity<>(ProdutoraDTOResp.paraDTO(resp),
        HttpStatus.CREATED);
  }

  /********************************* INATIVAR *********************************/

  @DeleteMapping("/{id}")
  public ResponseEntity<ProdutoraDTOResp> inativar(@PathVariable Long id) {

    Optional<Produtora> pesq = produtoraService.obterPorId(id, UsarFiltro.SIM);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    produtoraService.inativar(pesq.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }
}