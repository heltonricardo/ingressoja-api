package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Pedido;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoDTORespPagina {

  private final Integer ultimaPagina;
  private final Long totalPedidos;
  private final List<PedidoDTORespAnalise> pedidos;

  public static PedidoDTORespPagina paraDTO(Page<Pedido> pagina) {

    List<PedidoDTORespAnalise> pedidos = pagina.stream()
        .map(PedidoDTORespAnalise::paraDTO).collect(Collectors.toList());

    return new PedidoDTORespPagina(
        pagina.getTotalPages() - 1,
        pagina.getTotalElements(),
        pedidos);
  }
}
