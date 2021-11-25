package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto_in.PedidoDTO;
import io.github.heltonricardo.ingressoja.dto_out.PedidoDTOResp;
import io.github.heltonricardo.ingressoja.dto_out.PedidoDTORespAnalise;
import io.github.heltonricardo.ingressoja.dto_out.PedidoDTORespPagina;
import io.github.heltonricardo.ingressoja.model.Pedido;
import io.github.heltonricardo.ingressoja.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pedido")
public class PedidoController {

  private final PedidoService pedidoService;

  @Autowired
  public PedidoController(PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }

  /******************************* OBTER TODOS ********************************/

  @GetMapping("/pagina/{numeroPagina}")
  public ResponseEntity<PedidoDTORespPagina> obterTodosPorPagina(
      @PathVariable Integer numeroPagina) {

    Page<Pedido> pagina = pedidoService.obterTodos(numeroPagina);

    PedidoDTORespPagina resp = PedidoDTORespPagina.paraDTO(pagina);

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /******************************* OBTER POR ID *******************************/

  @GetMapping("/{id}")
  public ResponseEntity<PedidoDTOResp> obterPorId(@PathVariable Long id) {

    if (pedidoService.obterPorId(id).isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    PedidoDTOResp resp = PedidoDTOResp
        .paraDTO(pedidoService.obterPorId(id).get());

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<Map<String, String>> salvar(
      @RequestBody @Valid PedidoDTO dto) {

    String url = pedidoService.salvar(dto.paraObjeto());

    if (url == null)
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    Map<String, String> resp = new HashMap<>();
    resp.put("urlPagamento", url);

    return new ResponseEntity<>(resp, HttpStatus.CREATED);
  }

  /********************************* CANCELAR *********************************/

  @PutMapping("/{id}/cancelar")
  public ResponseEntity<?> utilizar(@PathVariable Long id) {

    Optional<Pedido> pesq = pedidoService.obterPorId(id);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    Pedido pedido = pesq.get();

    boolean sucesso = pedidoService.cancelar(pedido);

    return sucesso
        ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.CONFLICT);
  }
}
