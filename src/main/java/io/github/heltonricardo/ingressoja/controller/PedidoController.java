package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto_in.PedidoDTO;
import io.github.heltonricardo.ingressoja.dto_out.PedidoDTOResp;
import io.github.heltonricardo.ingressoja.model.Pedido;
import io.github.heltonricardo.ingressoja.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("pedido")
public class PedidoController {

  private final PedidoService pedidoService;

  @Autowired
  public PedidoController(PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }

  /******************************* OBTER TODOS ********************************/

  @GetMapping
  public ResponseEntity<List<PedidoDTOResp>> obterTodos() {

    List<PedidoDTOResp> resp = new ArrayList<>();

    pedidoService.obterTodos()
        .forEach(p -> resp.add(PedidoDTOResp.paraDTO(p)));

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /******************************* OBTER POR ID *******************************/

  @GetMapping("/{id}")
  public ResponseEntity<PedidoDTOResp> obterPorId(@PathVariable Long id) {

    if (pedidoService.obterPorId(id).isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    PedidoDTOResp resp = PedidoDTOResp
        .paraDTO(pedidoService.obterPorId(id).get());

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<PedidoDTOResp> salvar(@RequestBody @Valid PedidoDTO dto) {

    Pedido resp = pedidoService.salvar(dto.paraObjeto());

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    PedidoDTOResp dtoResp = PedidoDTOResp.paraDTO(resp);

    return new ResponseEntity<>(dtoResp, HttpStatus.CREATED);
  }
}
