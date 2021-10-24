package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Evento;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventoDTORespVendas {

  private final String titulo;
  private final Date inicio;
  private final Date termino;
  private final Integer totalIngressos;
  private final List<ItemPedidoDTOResp> itensPedido;

  public static EventoDTORespVendas paraDTO(Evento evento) {
    List<ItemPedidoDTOResp> itens =
        evento.getTiposDeIngresso()
            .stream()
            .flatMap(tipo -> tipo.getItensPedido().stream())
            .map(ItemPedidoDTOResp::paraDTO)
            .collect(Collectors.toList());

    return new EventoDTORespVendas(evento.getTitulo(), evento.getInicio(),
        evento.getTermino(), evento.getTotalIngressos(), itens);
  }
}
