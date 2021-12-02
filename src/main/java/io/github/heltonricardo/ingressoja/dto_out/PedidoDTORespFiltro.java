package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Pedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoDTORespFiltro {

  private final Integer totalPedidos;
  private final List<PedidoDTORespAnalise> pedidos;

  public static PedidoDTORespFiltro paraDTO(Iterable<Pedido> lista) {

    List<PedidoDTORespAnalise> pedidos =
        StreamSupport.stream(lista.spliterator(), false)
        .map(PedidoDTORespAnalise::paraDTO).collect(Collectors.toList());

    return new PedidoDTORespFiltro(
        pedidos.size(),
        pedidos);
  }
}
