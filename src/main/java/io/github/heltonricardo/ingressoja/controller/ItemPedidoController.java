package io.github.heltonricardo.ingressoja.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.heltonricardo.ingressoja.dto_out.ItemPedidoDTORespVerificar;
import io.github.heltonricardo.ingressoja.model.ItemPedido;
import io.github.heltonricardo.ingressoja.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/item-pedido")
public class ItemPedidoController {

  private final ItemPedidoService itemPedidoService;

  @Autowired
  public ItemPedidoController(
      ItemPedidoService itemPedidoService) {

    this.itemPedidoService = itemPedidoService;
  }

  /********************************* VALIDAR **********************************/

  @PutMapping("/validar")
  public ResponseEntity<ItemPedidoDTORespVerificar> validar(
      @RequestBody ObjectNode obj) {

    try {
      Long id = obj.get("id").asLong();
      String cpf = obj.get("cpf").asText();

      Optional<ItemPedido> pesq = itemPedidoService.obterPorId(id);

      if (pesq.isEmpty())
        throw new Exception();

      ItemPedido itemPedido = pesq.get();

      if (!itemPedido.cpfPertenceAoItem(cpf))
        throw new Exception();

      return new ResponseEntity<>(
          ItemPedidoDTORespVerificar.paraDTO(itemPedido), HttpStatus.OK);
    } //
    catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /********************************* UTILIZAR *********************************/

  @PatchMapping("/{id}")
  public ResponseEntity<?> utilizar(@PathVariable Long id) {

    Optional<ItemPedido> pesq = itemPedidoService.obterPorId(id);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    ItemPedido itemPedido = pesq.get();

    Boolean sucesso = itemPedidoService.utilizar(itemPedido);

    return sucesso
        ? new ResponseEntity<>(HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.CONFLICT);
  }
}
