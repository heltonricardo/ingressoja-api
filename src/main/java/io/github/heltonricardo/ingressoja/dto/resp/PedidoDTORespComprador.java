package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Pedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoDTORespComprador {

  private final Long id;
  private final Date dataHora;
  private final Double valorTotal;

  public static PedidoDTORespComprador paraDTO(Pedido pedido) {
    return new PedidoDTORespComprador(
        pedido.getId(), pedido.getDataHora(), pedido.getValorTotal());
  }
}
