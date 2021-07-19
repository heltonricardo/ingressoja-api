package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto.CompradorDTO;
import io.github.heltonricardo.ingressoja.dto.resp.CompradorDTOResp;
import io.github.heltonricardo.ingressoja.dto.resp.PedidoDTORespComprador;
import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.service.CompradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("comprador")
public class CompradorController {

  private final CompradorService compradorService;

  @Autowired
  public CompradorController(CompradorService compradorService) {

    this.compradorService = compradorService;
  }

  /******************************* OBTER TODOS ********************************/

  @GetMapping
  public ResponseEntity<List<CompradorDTOResp>> obterTodos() {

    List<CompradorDTOResp> resp = new ArrayList<>();

    compradorService.obterTodos()
        .forEach(c -> resp.add(CompradorDTOResp.paraDTO(c)));

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /******************************* OBTER POR ID *******************************/

  @GetMapping("/{id}")
  public ResponseEntity<CompradorDTOResp> obterPorId(@PathVariable Long id) {

    Optional<Comprador> pesq = compradorService.obterPorId(id);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    CompradorDTOResp resp = CompradorDTOResp.paraDTO(pesq.get());

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /****************************** OBTER PEDIDOS *******************************/

  @GetMapping("/{id}/pedidos")
  public ResponseEntity<List<PedidoDTORespComprador>> obterPedidos(
      @PathVariable Long id) {

    Optional<Comprador> pesq = compradorService.obterPorId(id);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    Comprador comprador = pesq.get();

    List<PedidoDTORespComprador> lista = new ArrayList<>();

    comprador.getPedidos()
        .forEach(p -> lista.add(PedidoDTORespComprador.paraDTO(p)));

    return new ResponseEntity<>(lista, HttpStatus.OK);
  }

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<CompradorDTOResp> salvar(
      @RequestBody CompradorDTO dto) {

    Comprador resp = compradorService.salvar(dto.paraObjeto());

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.CONFLICT);

    return new ResponseEntity<>(CompradorDTOResp.paraDTO(resp),
        HttpStatus.CREATED);
  }
}
