package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto.PedidoDTO;
import io.github.heltonricardo.ingressoja.dto.resp.PedidoDTOResp;
import io.github.heltonricardo.ingressoja.model.Pedido;
import io.github.heltonricardo.ingressoja.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<PedidoDTOResp> salvar(
      @RequestParam(name = "idEvento") Long idEvento,
      @RequestParam(name = "idComprador") Long idComprador,
      @RequestBody PedidoDTO dto) {

    Pedido resp = pedidoService.salvar(idEvento, idComprador,
        dto.paraObjeto());

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    PedidoDTOResp dtoResp = PedidoDTOResp.paraDTO(resp);

    return new ResponseEntity<>(dtoResp, HttpStatus.CREATED);
  }
}
