package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemPedidoDTOResp {

  private final Long id;
  private final String ingressante;
  private final String cpf;
  private final Boolean utilizado;
  private final Boolean valido;
  private final TipoDeIngressoDTORespItemPedido tipoDeIngresso;

  public static ItemPedidoDTOResp paraDTO(ItemPedido itemPedido) {

    return new ItemPedidoDTOResp(
        itemPedido.getId(),
        itemPedido.getIngressante(),
        itemPedido.getCpf(),
        itemPedido.getUtilizado(),
        itemPedido.getPedido().isStatusPedidoProcessado(),
        TipoDeIngressoDTORespItemPedido
            .paraDTO(itemPedido.getTipoDeIngresso()));
  }
}
