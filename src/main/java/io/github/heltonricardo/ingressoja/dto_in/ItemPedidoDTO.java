package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import lombok.Getter;

@Getter
public class ItemPedidoDTO {

  private String ingressante;
  private String cpf;
  private Long idTipoDeIngresso;

  public ItemPedido paraObjeto() {
    return new ItemPedido(ingressante, cpf, idTipoDeIngresso);
  }
}
