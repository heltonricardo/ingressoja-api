package io.github.heltonricardo.ingressoja.dto_out;

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
  private final Long idEvento;
  private final String tituloEvento;

  public static PedidoDTORespComprador paraDTO(Pedido pedido) {
    return new PedidoDTORespComprador(
        pedido.getId(), pedido.getDataHora(), pedido.getValorTotal(),
        pedido.getItensPedido().get(0).getTipoDeIngresso().getEvento().getId(),
        pedido.getItensPedido().get(0).getTipoDeIngresso().getEvento()
            .getTitulo());
  }
}
