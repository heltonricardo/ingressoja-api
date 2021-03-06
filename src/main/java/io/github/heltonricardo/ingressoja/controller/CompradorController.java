package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto_in.CompradorDTO;
import io.github.heltonricardo.ingressoja.dto_out.CompradorDTOResp;
import io.github.heltonricardo.ingressoja.dto_out.PedidoDTORespComprador;
import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.model.Pedido;
import io.github.heltonricardo.ingressoja.service.CompradorService;
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

    Optional<Comprador> pesq = compradorService.obterPorId(id, UsarFiltro.NAO);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    CompradorDTOResp resp = CompradorDTOResp.paraDTO(pesq.get());

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /****************************** OBTER PEDIDOS *******************************/

  @GetMapping("/{id}/pedidos")
  public ResponseEntity<List<PedidoDTORespComprador>> obterPedidos(
      @PathVariable Long id) {

    List<Pedido> pedidos = compradorService.obterPedidos(id);

    if (pedidos == null)
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    List<PedidoDTORespComprador> resp = new ArrayList<>();

    pedidos.forEach(p -> resp.add(PedidoDTORespComprador.paraDTO(p)));

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<CompradorDTOResp> salvar(
      @RequestBody @Valid CompradorDTO dto) {

    Comprador resp = compradorService.salvar(dto.paraObjeto());

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.CONFLICT);

    return new ResponseEntity<>(CompradorDTOResp.paraDTO(resp),
        HttpStatus.CREATED);
  }

  /******************************** ATUALIZAR *********************************/

  @PutMapping("/{id}")
  public ResponseEntity<CompradorDTOResp> atualizar(
      @RequestBody @Valid CompradorDTO dto, @PathVariable Long id) {

    Comprador resp = compradorService.atualizar(dto.paraObjeto(), id);

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.CONFLICT);

    return new ResponseEntity<>(CompradorDTOResp.paraDTO(resp),
        HttpStatus.OK);
  }

  /********************************* INATIVAR *********************************/

  @DeleteMapping("/{id}")
  public ResponseEntity<CompradorDTOResp> inativar(@PathVariable Long id) {

    Optional<Comprador> pesq = compradorService.obterPorId(id, UsarFiltro.SIM);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    compradorService.inativar(pesq.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
