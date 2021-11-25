package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Pedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoDTORespAnalise {

  private final Long id;
  private final Date dataHora;
  private final Double valorTotal;

  public static PedidoDTORespAnalise paraDTO(Pedido pedido) {

    return new PedidoDTORespAnalise(
        pedido.getId(),
        pedido.getDataHora(),
        pedido.getValorTotal());
  }
}
