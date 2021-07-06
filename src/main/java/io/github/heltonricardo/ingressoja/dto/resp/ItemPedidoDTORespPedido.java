package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemPedidoDTORespPedido {

  private Long id;
  private String ingressante;
  private String cpf;
  private String email;
  private TipoDeIngressoDTORespItemPedido tipoDeIngresso;

  public static ItemPedidoDTORespPedido paraDTO(ItemPedido itemPedido) {
    return new ItemPedidoDTORespPedido(itemPedido.getId(),
        itemPedido.getIngressante(), itemPedido.getCpf(),
        itemPedido.getEmail(),
        TipoDeIngressoDTORespItemPedido.paraDTO(
            itemPedido.getTipoDeIngresso()));
  }
}
