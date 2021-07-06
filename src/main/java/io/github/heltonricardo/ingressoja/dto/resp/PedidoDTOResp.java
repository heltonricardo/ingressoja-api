package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Pedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoDTOResp {

  private final Long id;
  private final LocalDateTime dataHora;
  private final String numeroCartao;
  private final Double valorTotal;
  private final List<ItemPedidoDTORespPedido> itensPedido;

  public static PedidoDTOResp paraDTO(Pedido pedido) {

    List<ItemPedidoDTORespPedido> itens = new ArrayList<>();

    pedido.getItensPedido()
        .forEach(i -> itens.add(ItemPedidoDTORespPedido.paraDTO(i)));

    return new PedidoDTOResp(
        pedido.getId(),
        pedido.getDataHora(),
        pedido.getNumeroCartao().
            substring(pedido.getNumeroCartao().length() - 4),
        pedido.getValorTotal(), itens);
  }
}
