package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto_in.PedidoDTO;
import io.github.heltonricardo.ingressoja.dto_out.PedidoDTOResp;
import io.github.heltonricardo.ingressoja.service.PedidoService;
import io.github.heltonricardo.ingressoja.utils.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pedido")
public class PedidoController {

  private final PedidoService pedidoService;

  @Autowired
  public PedidoController(PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }

  @GetMapping("/teste")
  public void teste() {
    Pagamento.consultarPagamento("21");
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
}
