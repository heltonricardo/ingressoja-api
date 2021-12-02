package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Pedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoDTORespAnalise {

  private final Long id;
  private final String cpf;
  private final Date dataHora;
  private final Double valorTotal;
  private final Double taxaPlataforma;

  public static PedidoDTORespAnalise paraDTO(Pedido pedido) {

    return new PedidoDTORespAnalise(
        pedido.getId(),
        pedido.getComprador().getCpf(),
        pedido.getDataHora(),
        pedido.getValorTotal(),
        pedido.calcularTaxaPlataforma()
    );
  }
}
