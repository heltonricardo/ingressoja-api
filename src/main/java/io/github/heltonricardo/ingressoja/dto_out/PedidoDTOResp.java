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
public class PedidoDTOResp {

  private final Long id;
  private final Date dataHora;
  private final Double valorTotal;
  private final String urlPagamento;
  private final EventoDTOResp evento;
  private final String statusPagamento;
  private final String statusPedido;
  private final List<ItemPedidoDTOResp> itensPedido;

  public static PedidoDTOResp paraDTO(Pedido pedido) {

    List<ItemPedidoDTOResp> itens = new ArrayList<>();

    pedido.getItensPedido()
        .forEach(i -> itens.add(ItemPedidoDTOResp.paraDTO(i)));

    return new PedidoDTOResp(
        pedido.getId(),
        pedido.getDataHora(),
        pedido.getValorTotal(),
        pedido.getUrlPagamento(),
        EventoDTOResp.paraDTO(pedido.getEvento()),
        pedido.getStatusPagamento(),
        pedido.getStatusPedido(), itens);
  }
}
