package io.github.heltonricardo.ingressoja.dto;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.Getter;

@Getter
public class ItemPedidoDTO {

  private String ingressante;
  private String cpf;
  private Long idTipoDeIngresso;

  public ItemPedido paraObjeto() {

    TipoDeIngresso tipoDeIngresso = new TipoDeIngresso();
    tipoDeIngresso.setId(idTipoDeIngresso);

    return new ItemPedido(ingressante, cpf, tipoDeIngresso);
  }
}
