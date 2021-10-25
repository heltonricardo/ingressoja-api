package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemPedidoDTORespVerificar {

  private final String ingressante;
  private final Boolean utilizado;
  private final String nomeTipoDeIngresso;
  private final String tituloEvento;

  public static ItemPedidoDTORespVerificar paraDTO(ItemPedido itemPedido) {

    return new ItemPedidoDTORespVerificar(
        itemPedido.getIngressante(),
        itemPedido.getUtilizado(),
        itemPedido.getTipoDeIngresso().getNome(),
        itemPedido.getTipoDeIngresso().getEvento().getTitulo()
    );
  }
}
